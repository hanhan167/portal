package com.hansy.portal.common.utils;

import java.io.InputStream;
import java.util.Properties;

public class Config {

	private static Config instance = null;

	private Properties properties = null;

	private Config() {
		init();
	}

	public static Config getInstance() {

		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	/**
	 * 初始化配置文件
	 */
	public void init() {

		try {
			InputStream is = Config.class
					.getResourceAsStream("/config/msg/msg-comm.properties");
			properties = new Properties();
			properties.load(is);

		} catch (Exception e) {
			throw new RuntimeException("没有找到msg-comm.properties文件!");
		}
	}

	/**
	 * 根据key值取得对应的value值
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return properties.getProperty(key);
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}
}
