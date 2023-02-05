package com.example.androiddemo.Activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.androiddemo.IMyAidlInterface;

public class AIDLService extends Service {

    private IBinder binder = new IMyAidlInterface.Stub() {
        @Override
        public int add(int value1, int value2) {
            return value1 + value2;
        }
    };

    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}