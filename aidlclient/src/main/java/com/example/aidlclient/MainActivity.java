package com.example.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.androiddemo.IMyAidlInterface;
import com.google.android.material.internal.ContextUtils;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection connection = new ServiceConnection() {

        private IMyAidlInterface aidl;

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            aidl = IMyAidlInterface.Stub.asInterface(iBinder);
            if (aidl != null) {
                try {
                    Log.e("aidl", "the result is: " + aidl.add(10, 15));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            aidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindMyService();
    }

    private void bindMyService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.androiddemo", "com.example.androiddemo.Activity.AIDLService"));
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
}