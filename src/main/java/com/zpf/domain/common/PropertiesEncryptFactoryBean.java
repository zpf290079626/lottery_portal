package com.zpf.domain.common;

import com.zpf.utils.AESUtil;
import java.util.Properties;
import org.springframework.beans.factory.FactoryBean;

public class PropertiesEncryptFactoryBean implements FactoryBean<Object> {
	private Properties properties;

	@Override
	public Object getObject() throws Exception {
		return getProperties();
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public Properties getProperties() {
		return properties;
	}

	/**
	 * 解密password密码
	 * 
	 * @Author adtec-maohuafeng
	 * @since 2014-12-11 上午9:34:02
	 * @version 1.0
	 * @param inProperties
	 */
	public void setProperties(Properties inProperties) {
		this.properties = inProperties;
		String password = inProperties.getProperty("password");
		if (password != null) {
			String newPassword = AESUtil.decrypt(password);
			properties.put("password", newPassword);
		}

	}

}
