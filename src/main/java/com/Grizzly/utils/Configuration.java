package com.Grizzly.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;


public class Configuration {

	public static String getproperty(String key) throws IOException {
		return load(key);
	}
	
	private static String load(String key) throws IOException {

		Properties properties = new Properties();
		try {        	 
			//properties.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config.properties"));
			properties.load(new FileInputStream("config.properties"));
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
		String value = properties.getProperty(key);
		if (StringUtils.isWhitespace(value)) {
			throw new NullPointerException("Value for " + key + " is empty in " /*+ props*/);
		}
		if (StringUtils.isBlank(value)) {
			throw new NullPointerException(key + " not found in " /*+ props*/);
		}

		return StringUtils.trim(value);
	}

    public static String framework(String s) {
		return "name";
    }
}
