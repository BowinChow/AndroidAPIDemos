package com.example.androiddemo.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class RegisterModelImpl implements IRegisterModel {
    @Override
    public void register(String account, String psw, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("data", Context.MODE_PRIVATE).edit();
        editor.putString("account", account);
        editor.putString("password", psw);
        editor.apply();
        Toast.makeText(context, "save successfully!", Toast.LENGTH_LONG).show();
    }
}
