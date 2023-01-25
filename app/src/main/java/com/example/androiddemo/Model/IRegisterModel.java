package com.example.androiddemo.Model;

import android.content.Context;
import android.view.View;

import com.example.androiddemo.View.ILoginView;

public interface IRegisterModel {

    void register(String account, String psw, Context context);
}
