package com.edward.io.jdk.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by edwardcheng on 2017/9/10.
 */
public class InetAddressExample {

    public static void main(String[] args) throws UnknownHostException {

        System.out.println(InetAddress.getLoopbackAddress().toString());
        System.out.println(InetAddress.getLocalHost().toString());
        System.out.println(Arrays.toString(InetAddress.getAllByName("www.baidu.com")));
        System.out.println(InetAddress.getByName("www.baidu.com"));
        System.out.println(Arrays.toString(InetAddress.getByName("www.baidu.com").getAddress()));
        System.out.println(InetAddress.getByAddress(InetAddress.getByName("www.baidu.com").getAddress()).toString());
        System.out.println((byte)213);
        System.out.println(((byte)213) & 0xFF);
    }
}
