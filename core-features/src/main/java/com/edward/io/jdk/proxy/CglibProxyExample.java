package com.edward.io.jdk.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by edwardcheng on 2017/9/14.
 */
public class CglibProxyExample {

    static class Handler {
        public void handle() {
            System.out.println("handle business");
        }
    }

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Handler.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println(String.format("%s's method[%s] is invoked", obj.getClass(), method.getName()));
                return proxy.invokeSuper(obj,args);
            }
        });
        Handler handler = (Handler)enhancer.create();
        handler.handle();
    }
}
