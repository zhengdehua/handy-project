package com.edward.io.third.log4j;

import org.apache.log4j.*;

/**
 * Created by edwardcheng on 2017/9/25.
 */
public class MDCExample {

    public static void main(String[] args) {
        String mdcPattern = "[%X{author}]";
        ConsoleAppender consoleAppender = new ConsoleAppender(
                new PatternLayout(mdcPattern));

        MDC.put("author", "EdwardCheng");

        Logger log = Logger.getLogger(MDCExample.class);
        log.addAppender(consoleAppender);

        log.debug("test log message");
    }
}
