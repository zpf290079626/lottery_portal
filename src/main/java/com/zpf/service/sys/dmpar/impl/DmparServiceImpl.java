package com.zpf.service.sys.dmpar.impl;

import com.zpf.domain.common.KeyValueVO;
import com.zpf.service.sys.dmpar.IDmparService;
import com.zpf.utils.DateUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangpengfei e-mail:pengfei19890227@163.com
 * @version 创建时间：2017年8月28日 下午11:49:37 类说明
 */
public class DmparServiceImpl implements IDmparService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DmparServiceImpl.class);

	private long updateGapHour = 10;// 更新间隔
	private Date lastUpdateTime;// 上一次更新时间
	private Map<String, List<KeyValueVO>> dmparMap = new HashMap<String, List<KeyValueVO>>();

	private final Object LOCK = new Object();

	@Override
	public Map<String, List<KeyValueVO>> getAllDmpar() {
		if (checkIsUpdate()) {
			synchronized (LOCK) {
				if (checkIsUpdate()) {
					// TODO
					lastUpdateTime = new Date();
					LOGGER.info("dmpar info update time is {}", lastUpdateTime);
				}
			}
		}
		return null;
	}

	@Override
	public List<KeyValueVO> getDmparByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean checkIsUpdate() {
		return MapUtils.isEmpty(dmparMap) || updateGapHour < DateUtil.getDiffHours(lastUpdateTime, new Date());
	}
}
