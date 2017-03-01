package com.example.sky.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.send_notice:
                        Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                        manager.cancel(1);   效果跟setAutoCancel（true）相同
                        Notification notification = new NotificationCompat.Builder(MainActivity.this)
                                .setContentTitle("title")
                                .setContentText("text")
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                                .setContentIntent(pendingIntent)
                                .setAutoCancel(true) //点击图标后通知图标自动消失
                                .setVibrate(new long[]{0,1000,1000,1000})//设置收到消息后震动，当前表示收到后立刻震动一秒，然后静止一秒，再震动一秒 要在配置文件中配置
                                .setLights(Color.GREEN,1000,1000)//设置前置LED灯亮的颜色，时常，和闪烁间隔
//                                .setDefaults(NotificationCompat.DEFAULT_ALL)  通知的默认效果，他会根据当前手机的环境来决定播放什么铃声以及如何震动
                                .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的重要程度
                                .build();
                        manager.notify(1,notification);
                        break;
                    default:
                        break;

                }

            }
        });
    }
}
