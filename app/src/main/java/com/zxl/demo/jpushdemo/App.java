package com.zxl.demo.jpushdemo;

import android.app.Application;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //notice 设置别名，可以成功
        JPushInterface.setAlias(this,100,"123456");

        //notice 设置Tag发送失败
//        Set<String> set = new HashSet<>();
//        set.add("aaa");
//        set.add("bbb");
//        JPushInterface.setTags(this,100,set);
        String registrationID = JPushInterface.getRegistrationID(this);

    }
}
