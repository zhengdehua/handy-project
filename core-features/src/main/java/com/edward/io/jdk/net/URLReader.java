package com.edward.io.jdk.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by edwardcheng on 2017/9/12.
 */
public class URLReader {

    public static void main(String[] args) {

        String site = "http://www.baidu.com";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new URL(site).openStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
