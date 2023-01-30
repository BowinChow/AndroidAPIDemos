package com.example.androiddemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.androiddemo.utils.ContextUtils;

public class DemoService extends Service {
    public DemoService() {
    }

    @Override
    public void onCreate() {
        Log.e(ContextUtils.TAG, "service on create");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(ContextUtils.TAG, "start onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(ContextUtils.TAG, "unbind service");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(ContextUtils.TAG, "service destroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(ContextUtils.TAG, "bind service");
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public void start() {
            Log.e(ContextUtils.TAG, "my Binder");
        }
    }
}