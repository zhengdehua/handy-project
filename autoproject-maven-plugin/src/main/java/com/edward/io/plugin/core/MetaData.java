package com.edward.io.plugin.core;

import java.util.List;

/**
 * Created by lori on 2017/8/15.
 */
public class MetaData {

    private String targetPath;
    private List<Item> items;

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item {
        private String basePackage;
        private String domain;
        private String author;
        private String date;

        public Item(String basePackage, String domain, String author, String date) {
            this.basePackage = basePackage;
            this.domain = domain;
            this.author = author;
            this.date = date;
        }

        public String getBasePackage() {
            return basePackage;
        }

        public void setBasePackage(String basePackage) {
            this.basePackage = basePackage;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
