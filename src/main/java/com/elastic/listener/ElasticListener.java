package com.elastic.listener;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.elastic.utils.PropertiesUtils;

public class ElasticListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		PropertiesUtils.pps = new Properties();
		
		try {
			String path = (getClass().getClassLoader().getResource("").toURI()).getPath();
			FileInputStream is = new FileInputStream(path + "elasticsearch.properties");
			PropertiesUtils.pps.load(is);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
