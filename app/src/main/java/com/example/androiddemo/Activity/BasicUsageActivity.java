package com.example.androiddemo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.example.androiddemo.R;
import com.example.androiddemo.service.DemoService;

public class BasicUsageActivity extends AppCompatActivity {

    AppCompatButton handlerButton;
    AppCompatButton notiButton;
    AppCompatButton serviceButton;
    AppCompatButton AIDLButton;
    private final static int MSG_SHOW_TOAST = 1;

    private DemoService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder = (DemoService.MyBinder) iBinder;
            if (myBinder != null) {
                Toast.makeText(BasicUsageActivity.this, "bind success", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private Handler mHandler = new Handler(message -> {
        switch (message.what) {
            case MSG_SHOW_TOAST:
                Toast.makeText(BasicUsageActivity.this, "update UI from thread", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return true;
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_usage);

        buttonClicked();

    }

    public void buttonClicked() {
        handlerButton = findViewById(R.id.basic_handler);
        notiButton = findViewById(R.id.basic_notification);
        serviceButton = findViewById(R.id.basic_service);
        AIDLButton  =findViewById(R.id.basic_aidl);

        handlerButton.setOnClickListener(view -> {

            new Thread(() -> {
                Message message = new Message();
                message.what = MSG_SHOW_TOAST;
                mHandler.sendMessage(message);
            }).start();
        });

        serviceButton.setOnClickListener(v -> {
            Intent bindIntent = new Intent(BasicUsageActivity.this, DemoService.class);
            bindService(bindIntent, connection, BIND_AUTO_CREATE);
        });
    }
}