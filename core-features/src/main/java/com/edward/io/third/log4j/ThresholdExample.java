package com.edward.io.third.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerRepository;

public class ThresholdExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BasicConfigurator.configure();

		Logger debugLog = Logger.getLogger("com.edward.io");
		debugLog.setLevel(Level.DEBUG);

		Logger infoLog = Logger.getLogger("com.edward.io.third.log4j");
		infoLog.setLevel(Level.INFO);

		LoggerRepository loggerRepository = infoLog.getLoggerRepository();

		loggerRepository.setThreshold(Level.DEBUG);
		debugLog.debug("debug message");
		infoLog.debug("debug message");

		loggerRepository.setThreshold(Level.INFO);
		debugLog.info("info messsage");
		infoLog.info("info message");

		loggerRepository.setThreshold(Level.WARN);
		debugLog.warn("warn message");
		infoLog.warn("warn message");

	}

}
