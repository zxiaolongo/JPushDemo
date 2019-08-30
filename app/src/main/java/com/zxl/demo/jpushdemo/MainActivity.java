package com.zxl.demo.jpushdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zxl.demo.jpushdemo.service.MyJCommonService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //启动后开启服务，否则关闭app收不到通知（原因未知）
        startService(new Intent(this,MyJCommonService.class));
    }
}
