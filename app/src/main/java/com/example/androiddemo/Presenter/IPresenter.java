package com.example.androiddemo.Presenter;

import com.example.androiddemo.LoginListener.ILoginListener;

public interface IPresenter {
    void doLogin(String account, String pwd);
    void doRequest();
    void doRegister(String account, String pwd);
}
