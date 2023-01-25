package com.example.androiddemo.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.androiddemo.LoginListener.ILoginListener;
import com.example.androiddemo.retrofit.Bean;
import com.example.androiddemo.retrofit.GetService;
import com.example.androiddemo.utils.ContextUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class LoginModelImpl implements ILoginModel {
    private final String TAG = "MVP";

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void login(String account, String pwd, ILoginListener listener) {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String originAcc = null;
            String originPwd = null;
            SharedPreferences preferences = ContextUtils.context.getSharedPreferences("data", Context.MODE_PRIVATE);
            originAcc = preferences.getString("account", null);
            originPwd = preferences.getString("password", null);
            Log.e(TAG, "the account is: " + originAcc + " password: " + originPwd);
            if (originAcc == null && originPwd == null) {
                handler.post(listener::onToast);
            }else if (account.equals(originAcc) && pwd.equals(originPwd)) {
                handler.post(listener::onSuccess);
            } else {
                handler.post(listener::onFailure);
            }
        }).start();
    }

    @Override
    public void request(ILoginListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://op.juhe.cn/")
                // 增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                // 增加返回值为Gson的支持
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 接口实体，返回的是String
        GetService phoneService = retrofit.create(GetService.class);
        Call<String> call = phoneService.getString("你好", "2833a660902644508778b5dfd452c080");
        // 发送请求
        call.enqueue(new Callback<String>() {
            // 响应回调
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse = \n"
                        + "response.message() = " + response.message() + "\n"
                        + "response.body() = " + response.body());
                listener.onSuccess();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure = \n" + t.toString());
                listener.onFailure();
            }

        });
    }
}
