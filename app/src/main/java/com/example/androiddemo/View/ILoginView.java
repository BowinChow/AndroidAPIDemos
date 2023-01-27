package com.example.androiddemo.View;

public interface ILoginView {
    void showProgressBar();

    void hideProgressBar();

    void makeToast(String content);

    void startAnotherActivity();
    void buttonSubmitSuccess();
    void buttonSubmitFailed();
    void buttonReset();

}
