package com.zpf.utils;

import com.zpf.domain.common.KeyInfoDomain;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class KeyGenerator implements ApplicationContextAware {
	public static final int MAX = 50;
	private static final Log LOGGER = LogFactory.getLog(KeyGenerator.class);

	private static ConcurrentHashMap<String, KeyInfoDomain> map = new ConcurrentHashMap<String, KeyInfoDomain>(MAX);
	private static DataSourceTransactionManager txManager;
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		context = ctx;

	}

	public void init() {
		txManager = (DataSourceTransactionManager) context.getBean("transactionManager");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("主键生成器初始化，测试事务对象");
			LOGGER.debug(context);
		}
	}

	/**
	 * 获得主键<br>
	 * 获取业务流水号（3位所属系统编码+2位系统编码，默认00+8位日期+10位序号） 参数为数据库中的PK_NAME字段
	 * 
	 * @Author SmallTree
	 * @since 2013-12-20 上午10:03:06
	 * @version 1.0
	 * @param keyName
	 * @return
	 */
	public static String getKey(String keyName) {
		long nextKey = getNextKeyinfo(keyName);
		KeyInfoDomain ki = map.get(keyName);
		String nextStringKey = getBusInessKey(ki.getKeyLen(), ki.getSysCd(), nextKey);

		return nextStringKey;
	}

	/**
	 * 获取10位序号
	 * 
	 * @Author adtec-maohuafeng
	 * @since 2014-5-21 上午11:17:06
	 * @version 1.0
	 * @param keyName
	 * @return
	 */
	public static long getNextKey(String keyName) {
		long nextKey = getNextKeyinfo(keyName);
		return nextKey;
	}

	/**
	 * 获取10位序号
	 * 
	 * @Author adtec-maohuafeng
	 * @since 2014-5-21 上午11:17:06
	 * @version 1.0
	 * @param keyName
	 * @return
	 */
	public static Integer getNextIntKey(String keyName) {
		Long nextKey = getNextKeyinfo(keyName);
		nextKey = new Long(nextKey);

		return nextKey.intValue();
	}

	/**
	 * 获取业务流水号（3位所属系统编码+2位系统编码，默认00+8位日期+10位序号）
	 * 
	 * @Author adtec-maohuafeng
	 * @since 2014-5-21 上午11:13:23
	 * @version 1.0
	 * @param
	 * @return
	 */
	private static String getBusInessKey(int keyLen, String sysCd, long nextKey) {
		String reg = "%0" + keyLen + "d";
		String nextKeyStr = String.format(reg, nextKey);
		if (!StringUtils.isBlank(sysCd)) {
			String machingCd = "00";// 2位系统编码，默认00
			String date = DateUtil.getMachingCurrentDate().replaceAll("-", "");// 8位日期
			StringBuilder sb = new StringBuilder();
			sb.append(sysCd);
			sb.append(machingCd);
			sb.append(date);
			sb.append(nextKeyStr);
			nextKeyStr = sb.toString();
		}
		return nextKeyStr;
	};

	/**
	 * keyinfo
	 * 
	 * @param keyName
	 * 
	 * @return
	 */
	private static long getNextKeyinfo(String keyName) {
		KeyInfoDomain keyinfo = (KeyInfoDomain) map.get(keyName);

		if (keyinfo == null) {
			synchronized (map) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("keyinfo为空");
				}
				keyinfo = (KeyInfoDomain) map.get(keyName);
				if (keyinfo == null) {
					keyinfo = new KeyInfoDomain();
					keyinfo.setPkName(keyName);
					map.put(keyName, keyinfo);
				}
			}

		}
		try {
			keyinfo.getLock().lock();
			long nextKey = keyinfo.getNextKey();
			if (nextKey >= keyinfo.getMaxVal()) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("nextKey > keyinfo.getKeyMax(),nextKey =" + nextKey + ",keyinfo" + keyinfo);
				}
				retrieveFromDB(keyinfo);
			} else {
				nextKey++;
				keyinfo.setNextKey(nextKey);
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("keyinfo=" + keyinfo);
			}
			return keyinfo.getNextKey();
		} finally {
			keyinfo.getLock().unlock();
		}
	}

	/**
	 * 
	 * @param keyinfo
	 */
	private static void retrieveFromDB(KeyInfoDomain keyinfo) {
		DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		td.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
		td.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		TransactionStatus ts = txManager.getTransaction(td);
		try {

			JdbcTemplate jt = new JdbcTemplate(txManager.getDataSource());

			int n = jt.update("update AUTOMATIC_PMKEY set MAX_VAL = MAX_VAL+STEP_LEN where PK_NAME=?",
					new Object[] { keyinfo.getPkName() });
			if (n != 1) {
				throw new Exception("AUTOMATIC_PMKEY中找到了多条记录：" + keyinfo.getPkName() + ",记录数 :" + n);
			}

			Map<?, ?> row = jt.queryForMap("select * from AUTOMATIC_PMKEY where PK_NAME=?",
					new Object[] { keyinfo.getPkName() });
			if (row == null) {
				throw new Exception("AUTOMATIC_PMKEY," + keyinfo.getPkName());
			}

			keyinfo.setMaxVal(((Number) row.get("MAX_VAL")).longValue());
			keyinfo.setStepLen(((Number) row.get("STEP_LEN")).intValue());
			keyinfo.setKeyLen(((Number) row.get("PK_LEN")).intValue());
			keyinfo.setSysCd(row.get("SYS_CD") == null ? "" : row.get("SYS_CD").toString());

			keyinfo.setNextKey(keyinfo.getMaxVal() - keyinfo.getStepLen() + 1);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("resetted the keyinfo with" + keyinfo);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			txManager.rollback(ts);
		}

		txManager.commit(ts);

	}
}
