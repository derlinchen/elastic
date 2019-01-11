package com.elastic.utils;

import java.util.Properties;

public class PropertiesUtils {
	public static Properties pps;
	
	public static String GetString(String key) {
		return pps.getProperty(key);
		
	}
}
