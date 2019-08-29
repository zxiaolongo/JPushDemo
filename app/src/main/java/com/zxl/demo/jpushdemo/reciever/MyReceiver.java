package com.zxl.demo.jpushdemo.reciever;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONObject;
import cn.jpush.android.api.JPushInterface;

public class MyReceiver extends BroadcastReceiver {
    String TAG = "tag";
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Log.d(TAG, "JPush 用户注册成功");
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的自定义消息");
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            // notice:  根据极光的答复，初步的解决方案应该是要修改后台代码，生成PushPayload时只生成Message，
            // notice 不要Notification，或者推送的Notification中Message或Alert为空，这样极光SDK就不会在状态
            // notice 栏生成Notification，然后Android设备在Action为 cn.jpush.android.intent.MESSAGE_RECEIVED 
            // notice 的Receive中根据业务逻辑生成相应的Notification并设置相应的PendingIntent。
            Log.d(TAG, "接受到推送下来的通知");
            receivingNotification(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");
            // notice:  点击通知触发 
            openNotification(context, bundle);

        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        Log.d(TAG, " title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        Log.d(TAG, "message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.d(TAG, "extras : " + extras);
    }
    private void openNotification(Context context, Bundle bundle) {
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String nMsgId = "", vcType = "";
        try {
            JSONObject extrasJson = new JSONObject(extras);
            nMsgId = extrasJson.optString("nMsgId");
            vcType = extrasJson.optString("vcType");
        } catch (Exception e) {
            Log.w(TAG, "Unexpected: extras is not a valid json", e);
            return;
        }
        if (!TextUtils.isEmpty(vcType)) {
            if (vcType.equals("消息通知")) {
            }
            if (vcType.equals("审批通知")) {
            }
        }
    }

}
