package com.edward.io.third.log4j;

import org.apache.log4j.*;

/**
 * Created by edwardcheng on 2017/9/25.
 */
public class NDCExample {

    public static void main(String[] args) {

        String ndcPattern = "[%x]";
        ConsoleAppender consoleAppender = new ConsoleAppender(
                new PatternLayout(ndcPattern));

        NDC.push("example message");

        Logger log = Logger.getLogger(NDCExample.class);
        log.addAppender(consoleAppender);

        log.debug("test log message");
    }
}
