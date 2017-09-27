package com.edward.io.third.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class LevelExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BasicConfigurator.configure();

		Logger debugLog = Logger.getLogger("com.edward.io");
		debugLog.setLevel(Level.DEBUG);

		Logger infoLog = Logger.getLogger("com.edward.io.third");
		infoLog.setLevel(Level.INFO);

		Logger warnLog = Logger.getLogger("com.edward.io.third.log4j");
		warnLog.setLevel(Level.WARN);

		debugLog.debug("debug message");
		debugLog.info("debug message");

		infoLog.debug("info message");
		infoLog.info("info message");
		infoLog.warn("info message");

		warnLog.info("warn message");
		warnLog.warn("warn message");
		warnLog.error("warn message");

	}

}
	