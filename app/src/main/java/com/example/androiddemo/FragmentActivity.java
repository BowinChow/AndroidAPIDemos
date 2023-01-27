package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androiddemo.Presenter.presenterImpl;
import com.example.androiddemo.View.ILoginView;
import com.example.androiddemo.utils.ContextUtils;

public class FragmentActivity extends AppCompatActivity implements ILoginView {

    private presenterImpl presenter;
    private String account;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        //TODO: add click event.
        Button registerButton = findViewById(R.id.register_btn);
        presenter = new presenterImpl(this);
        registerButton.setOnClickListener(v -> {
            initData();
            if (account != null && pwd != null) {
                presenter.doRegister(account, pwd);
            } else {
                makeToast("wrong account or password!");
            }

        });

    }

    public void initData() {
        EditText text_acc = findViewById(R.id.mvp_acc);
        account = text_acc.getText().toString();
        EditText text_pwd = findViewById(R.id.mvp_psw);
        pwd = text_pwd.getText().toString();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void makeToast(String content) {
        Toast.makeText(FragmentActivity.this, content, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startAnotherActivity() {

    }

    @Override
    public void buttonSubmitSuccess() {

    }

    @Override
    public void buttonSubmitFailed() {

    }

    @Override
    public void buttonReset() {

    }
}