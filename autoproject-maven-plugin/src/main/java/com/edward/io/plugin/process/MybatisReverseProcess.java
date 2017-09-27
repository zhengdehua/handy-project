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
package com.edward.io.plugin.process;

import com.edward.io.plugin.core.BuildContext;
import com.edward.io.plugin.core.Process;
import com.edward.io.plugin.core.ProcessException;
import com.edward.io.plugin.execute.MybatisReverseByConfigFileExecutor;
import com.edward.io.plugin.execute.MybatisReverseByJdbcExecutor;
import com.edward.io.plugin.core.DataBaseConfig;
import com.edward.io.plugin.execute.MybatisRuntimeConfigExecutor;
import com.edward.io.plugin.util.Assert;

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
public class MybatisReverseProcess implements Process {

    private String generateConfig;
    private DataBaseConfig dataBaseConfig;
    private BuildContext buildContext;

    public MybatisReverseProcess(String generateConfig, DataBaseConfig dataBaseConfig, BuildContext buildContext) {
        this.generateConfig = generateConfig;
        this.dataBaseConfig = dataBaseConfig;
        this.buildContext = buildContext;
    }

    public void process() throws ProcessException {
        Assert.isTrue((generateConfig != null
                && generateConfig.trim().length() > 0)
                || dataBaseConfig != null, "Can't find any database configuration");

        if (generateConfig != null) {
            new MybatisReverseByConfigFileExecutor(generateConfig, buildContext).execute();
        }else {
            new MybatisReverseByJdbcExecutor(dataBaseConfig, buildContext).execute();
        }

        new MybatisRuntimeConfigExecutor(buildContext).execute();
    }
}
