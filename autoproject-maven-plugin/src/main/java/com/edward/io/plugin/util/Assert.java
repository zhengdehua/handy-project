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

import java.lang.reflect.Array;
import java.util.Collection;

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
public final class Assert {

    public static void notNull(Object object, String message) {
        if (object == null) throw new IllegalArgumentException(message);
    }

    public static void notNull(Object object) {
        notNull(object, "argument can not be null");
    }

    public static void hasText(String str, String message) {
        if (str == null || str.trim().length() == 0) throw new IllegalArgumentException(message);
    }

    public static void hasText(String str) {
        hasText(str, "argument can not be empty");
    }

    public static void isTrue(Boolean expression, String message) {
        if (!expression) throw new IllegalArgumentException(message);
    }

    public static void isTrue(Boolean expression) {
        isTrue(expression, "argument can not be false");
    }

    public static void notEmpty(Object object, String message) {
        notNull(object, message);
        if (object instanceof Collection && ((Collection)object).size() == 0)
            throw new IllegalArgumentException(message);
        if (object.getClass().isArray() && Array.getLength(object) == 0)
            throw new IllegalArgumentException(message);
    }

    public static void notEmpty(Object object) {
        notEmpty(object, "argument can not be empty");
    }

}
