package com.example.androiddemo.Presenter;

import android.content.Context;
import android.view.View;

import com.example.androiddemo.LoginListener.ILoginListener;
import com.example.androiddemo.Model.ILoginModel;
import com.example.androiddemo.Model.IRegisterModel;
import com.example.androiddemo.Model.LoginModelImpl;
import com.example.androiddemo.Model.RegisterModelImpl;
import com.example.androiddemo.View.ILoginView;
import com.example.androiddemo.utils.ContextUtils;

public class presenterImpl implements IPresenter {
    private final ILoginModel model;
    private final IRegisterModel registerModel;
    private final ILoginView view;

    public presenterImpl(ILoginView view) {
        model = new LoginModelImpl();
        registerModel = new RegisterModelImpl();
        this.view = view;
    }

    @Override
    public void doLogin(String account, String pwd) {
        view.showProgressBar();
        model.login(account, pwd, new ILoginListener() {
            @Override
            public void onSuccess() {

                view.buttonSubmitSuccess();
                view.hideProgressBar();
                view.makeToast("login successfully!");
            }

            @Override
            public void onFailure() {
                view.hideProgressBar();
                view.buttonSubmitFailed();
                view.makeToast("login failed!");
            }

            @Override
            public void onToast() {
                view.hideProgressBar();
                view.makeToast("please register first!");
            }
        });
    }

    @Override
    public void doRequest() {
        model.request(new ILoginListener() {
            @Override
            public void onSuccess() {
                view.makeToast("retrofit demo works!");
            }

            @Override
            public void onFailure() {
                view.makeToast("retrofit demo failed!");

            }

            @Override
            public void onToast() {

            }
        });
    }

    @Override
    public void doRegister(String account, String pwd) {
        Context context = ContextUtils.context;
        registerModel.register(account,pwd,context);
    }
}
