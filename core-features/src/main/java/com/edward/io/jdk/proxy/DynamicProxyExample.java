package com.edward.io.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by edwardcheng on 2017/9/14.
 */
public class DynamicProxyExample {

    interface Handler {
        void handle();
    }

    public static void main(String[] args) {

        Handler handler = (Handler)Proxy.newProxyInstance(DynamicProxyExample.class.getClassLoader(), new Class[]{Handler.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(String.format("%s's method[%s] is invoked", proxy.getClass(), method.getName()));
                return null;
            }
        });

        handler.handle();

    }
}
