package com.auto.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;

public class MyApplicationContextListener implements ServletContextListener {
	private Log LOGGER = LogFactory.getLog(MyApplicationContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			AbandonedConnectionCleanupThread.shutdown();
		} catch (InterruptedException e1) {
			LOGGER.error("error in closing abandoned connections : ", e1);
		}
		try {
			LOGGER.info("deRegitering the Driver..");
			Enumeration<Driver> driver = DriverManager.getDrivers();
			while (driver.hasMoreElements()) {
				Driver dr = driver.nextElement();
				DriverManager.deregisterDriver(dr);
			}
		} catch (SQLException e) {
			LOGGER.error("error in unregistering jdbc driver", e);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		LOGGER.info("Application context started successfully..");
	}

}
