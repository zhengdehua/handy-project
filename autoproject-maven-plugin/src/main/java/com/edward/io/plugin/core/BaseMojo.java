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

import com.edward.io.plugin.util.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuildingRequest;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

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

public abstract class BaseMojo extends AbstractMojo{

    protected BuildContext buildContext;

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            //Set global logger
            Logger.setInstance(getLog());
            //Set build environment
            setBuildContext();

            Logger.tip(buildContext.toString());

            run();
        } catch (Exception e) {
            Logger.error(e);
            throw new MojoExecutionException("", e);
        }
    }

    protected abstract void run() throws ProcessException;

    private void setBuildContext() throws ProcessException {
        MavenProject project = (MavenProject)getPluginContext().get("project");
        buildContext = new BuildContext();
        buildContext.setGroupId(project.getGroupId());
        buildContext.setArtifactId(project.getArtifactId());
        buildContext.setBasedir(project.getBasedir().getAbsolutePath());
        buildContext.setDate(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));

        MavenProject _project = project;
        ProjectBuildingRequest buildingRequest = null;
        Properties properties = null;
        while (_project != null
                && ((buildingRequest = getBuildConfiguration(_project)) == null
                || (properties = buildingRequest.getSystemProperties()) == null))
        {
            _project = _project.getParent();
        }

        if (properties != null) {
            buildContext.setAuthor((String) properties.get("user.name"));
        }
    }

    private ProjectBuildingRequest getBuildConfiguration(MavenProject project) throws ProcessException {
        try {
            Field field = MavenProject.class.getDeclaredField("projectBuilderConfiguration");
            field.setAccessible(true);
            ProjectBuildingRequest buildingRequest = (ProjectBuildingRequest)field.get(project);
            return buildingRequest;
        } catch (Exception e) {
           throw new ProcessException(e);
        }
    }

}
