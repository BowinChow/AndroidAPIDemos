package com.example.androiddemo.Model;

import com.example.androiddemo.LoginListener.ILoginListener;

public interface ILoginModel {
    void login(String account, String pwd, ILoginListener listener);
    void request(ILoginListener listener);
}
