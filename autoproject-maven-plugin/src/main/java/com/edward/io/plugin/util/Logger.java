/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.edward.io.plugin.util;

import org.apache.maven.plugin.logging.Log;

/*************************************************************
*                 name: Edward Cheng 
*               gender: male 
*                  age: 27 
*               degree: undergraduate 
*               career: Java software engineer 
*            work time: 5 years 
*  master dev language: Java, JavaScript, HTML, SQL 
* master dev component: spring-framework, spring-boot, spring-cloud, spring-security, jquery, bootstrap, angularjs 
* growing dev language: C, Scala, Go, Python 
*                email: dwarcheng@163.com 
*               slogan: Interest always comes first. 
*************************************************************/
public final class Logger {

    private static Log instance;

    private Logger() {}

    public static void setInstance(Log log) {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = log;
                }
            }
        }
    }

    public static void tip(CharSequence content) {
        info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        info(content);
        info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public static void info( CharSequence content ) {
        if (instance != null) {
            instance.info(content);
        }
    }

    public static void info( CharSequence content, Throwable error ) {
        if (instance != null) {
            instance.info(content, error);
        }
    }

    public static void info( Throwable error ) {
        if (instance != null) {
            instance.info(error);
        }
    }

    public static void error( CharSequence content ) {
        if (instance != null) {
            instance.error(content);
        }
    }

    public static void error( CharSequence content, Throwable error ) {
        if (instance != null) {
            instance.error(content, error);
        }
    }

    public static void error( Throwable error ) {
        if (instance != null) {
            instance.error(error);
        }
    }
}
