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
package com.edward.io.plugin.execute;

import com.edward.io.plugin.core.Execute;
import com.edward.io.plugin.util.Logger;
import com.edward.io.plugin.core.BuildContext;
import com.edward.io.plugin.core.ProcessException;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
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
public class MybatisReverseByConfigFileExecutor implements Execute {

    private String generateConfig;
    private BuildContext buildContext;

    public MybatisReverseByConfigFileExecutor(String generateConfig, BuildContext buildContext) {
        this.generateConfig = generateConfig;
        this.buildContext = buildContext;
    }

    public Object execute() throws ProcessException {
        Logger.tip("Begin to execute mybatis reverse generate");

        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            File configFile = new File(generateConfig);
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            return null;
        } catch (Exception e) {
            throw new ProcessException(e);
        }
    }
}
