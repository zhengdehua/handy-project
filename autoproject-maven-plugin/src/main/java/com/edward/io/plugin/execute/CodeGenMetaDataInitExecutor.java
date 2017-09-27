package com.edward.io.plugin.execute;


import com.edward.io.plugin.core.*;
import com.edward.io.plugin.util.Logger;
import com.edward.io.plugin.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lori on 2017/8/15.
 */
public class CodeGenMetaDataInitExecutor implements Execute {

    private String targetPath;
    private BuildContext buildContext;

    public CodeGenMetaDataInitExecutor(String targetPath, BuildContext buildContext) {
        this.targetPath = targetPath;
        this.buildContext = buildContext;
    }

    public Object execute() throws ProcessException {
        Logger.tip("Begin to execute Meta data initialization");

        List<String> domains = new ArrayList<String>();

        for (String tableName : DataBaseConfig.getInstance().getTables()) {
            String[] splitNames = tableName.split("_");
            String domain = "";
            for (String splitName : splitNames) {
                domain += StringUtil.upperCaseFirstLetter(splitName);
            }
            if (domain.trim().length() > 0) {
                domains.add(domain);
            }
        }

        String author = buildContext.getAuthor();
        String basePackage = buildContext.getBasePackage();
        String date = buildContext.getDate();
        List<MetaData.Item> items = new ArrayList<MetaData.Item>();

        MetaData metaData = new MetaData();
        metaData.setTargetPath(targetPath);
        metaData.setItems(items);

        for (String name : domains) {
            items.add(new MetaData.Item(basePackage, name, author, date));
        }

        return metaData;
    }

}
