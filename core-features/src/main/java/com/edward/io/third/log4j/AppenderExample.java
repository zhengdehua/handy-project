package com.edward.io.third.log4j;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class AppenderExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		String projectPath = System.getProperty("user.dir") + "/core-features/";
		String outputPath = projectPath + "logs/";

		ConsoleAppender consoleAppender = new ConsoleAppender(new SimpleLayout());
		FileAppender rootAppender = new FileAppender(new SimpleLayout(), outputPath + "root.log");
		FileAppender appAppender = new FileAppender(new SimpleLayout(), outputPath + "app.log");
		FileAppender serviceAppender = new FileAppender(new SimpleLayout(), outputPath + "service.log");
		FileAppender secureAppender = new FileAppender(new SimpleLayout(), outputPath + "secret.log");

		Logger rootLog = Logger.getRootLogger();
		rootLog.addAppender(rootAppender);
		rootLog.addAppender(consoleAppender);

		rootLog.debug("root message");
		rootLog.info("root message");

		Logger appLog = Logger.getLogger("com.edward.io");
		appLog.addAppender(appAppender);

		appLog.debug("app message");
		appLog.info("app message");

		Logger serviceLog = Logger.getLogger("com.edward.io.third.log4j");
		serviceLog.addAppender(serviceAppender);
		serviceLog.debug("service message");
		serviceLog.info("service message");

		Logger secureLog = Logger.getLogger("com.edward.io.secure");
		secureLog.addAppender(secureAppender);
		secureLog.setAdditivity(false);

		secureLog.debug("secure message");
		secureLog.info("secure message");
		
	}

}
