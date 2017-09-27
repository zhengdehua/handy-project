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
package com.edward.io.plugin;

import com.edward.io.plugin.core.*;
import com.edward.io.plugin.process.CodeGenProcess;
import com.edward.io.plugin.process.MybatisReverseProcess;
import com.edward.io.plugin.util.Logger;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

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
@Mojo(name = "mybatis-reverse")
public class MybatisReverseMojo extends BaseMojo {

    @Parameter
    private String generateConfig;

    @Parameter
    private DataBaseConfig dataBaseConfig;

    protected void run() throws ProcessException {
        Logger.info("GENERATOR CONFIG --> " + generateConfig);
        if (dataBaseConfig != null) {
            Logger.info(dataBaseConfig.toString());
        }
        //Process mybatis reverse engineering
        new MybatisReverseProcess(generateConfig, dataBaseConfig, buildContext).process();
        BuildContext.getInstance().setOrmReverse(Constant.MYBATIS_REVERSE);
        new CodeGenProcess(buildContext).process();
    }
}
