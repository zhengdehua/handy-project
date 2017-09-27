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
package com.edward.io.plugin.core;

import java.util.List;

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
public class DataBaseConfig {

    public static final String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

    private static DataBaseConfig instance;

    private String driverClass;
    private String url;
    private String userId;
    private String password;
    private List<String> tables;

    public DataBaseConfig() {
        synchronized (DataBaseConfig.class) {
            if (instance == null) {
                instance = this;
            }
        }
    }

    public boolean fromOracle() {
        return driverClass.equals(ORACLE_DRIVER);
    }

    public static DataBaseConfig getInstance() {
        return instance;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DataBaseConfiguration:\n");
        sb.append("driverClass --> ").append(driverClass).append('\n');
        sb.append("url --> ").append(url).append('\n');
        sb.append("userId --> ").append(userId).append('\n');
        sb.append("password --> ").append(password).append('\n');
        if (tables != null && tables.size() > 0) {
            sb.append("tables: [");
            for (String table : tables) {
                sb.append(table).append(",");
            }
            if (sb.lastIndexOf(",") == sb.length()-1) {
                sb.deleteCharAt(sb.length()-1);
            }
            sb.append("]");
        }
        return sb.toString();
    }
}
