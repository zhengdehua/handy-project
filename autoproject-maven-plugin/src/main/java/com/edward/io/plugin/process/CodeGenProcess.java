package com.edward.io.plugin.process;


import com.edward.io.plugin.core.BuildContext;
import com.edward.io.plugin.core.MetaData;
import com.edward.io.plugin.core.ProcessException;
import com.edward.io.plugin.execute.CodeGenExecutor;
import com.edward.io.plugin.execute.CodeGenMetaDataInitExecutor;
import com.edward.io.plugin.core.Process;

import java.util.Arrays;

/**
 * Created by lori on 2017/8/15.
 */
public class CodeGenProcess implements Process {

    private BuildContext buildContext;

    public CodeGenProcess(BuildContext buildContext) {
        this.buildContext = buildContext;
    }

    public void process() throws ProcessException {

        String targetPath = buildContext.getBasedir() + "/src/main/java/"
                + delimitPackagePath(buildContext.getBasePackage());

        MetaData metaData = (MetaData) new CodeGenMetaDataInitExecutor(targetPath, buildContext).execute();
        new CodeGenExecutor(metaData, buildContext).execute();
    }

    private String delimitPackagePath(String packagePath) {
        String path = packagePath;
        String[] pathArr = packagePath.split("\\.");
        if (pathArr.length > 1) {
            path = "";
            for (String p : Arrays.asList(pathArr)) {
                path += (p + "/");
            }
        }
        return path;
    }

}
