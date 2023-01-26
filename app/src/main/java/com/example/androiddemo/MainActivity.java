package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androiddemo.Presenter.IPresenter;
import com.example.androiddemo.Presenter.presenterImpl;
import com.example.androiddemo.View.ILoginView;
import com.example.androiddemo.utils.ContextUtils;
import com.example.widget.view.SubmitButton;

public class MainActivity extends AppCompatActivity implements ILoginView {
    private presenterImpl presenter;
    private EditText mvpPsw;
    private ProgressBar pb;
    private EditText mvpAccount;
    private CheckBox CB;
    SubmitButton mvpButton;
    SubmitButton mvpRetrofitBtn;
    SubmitButton listButton;
    SubmitButton registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mvpButton = findViewById(R.id.mvp_btn);
        mvpRetrofitBtn = findViewById(R.id.mvp_retrofit);
        listButton = findViewById(R.id.list_btn);
        registerButton = findViewById(R.id.mvp_register);

        ContextUtils.registerActivity(MainActivity.this);

        mvpAccount = findViewById(R.id.mvp_acc);
        mvpPsw = findViewById(R.id.mvp_psw);
        pb = findViewById(R.id.mvp_prb);

        CB = findViewById(R.id.mvp_check);
        mvpPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());

        CB.setOnCheckedChangeListener((compoundButton, b) -> {
            if (CB.isChecked()) {
                mvpPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                mvpPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        presenter = new presenterImpl(this);

        mvpButton.setOnClickListener(v -> {
            presenter.doLogin(mvpAccount.getText().toString(), mvpPsw.getText().toString());
        });

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
            startActivity(intent);
        });
        mvpRetrofitBtn.setOnClickListener(v -> {
            presenter.doRequest();
        });

        listButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
        });


    }

    @Override
    public void showProgressBar() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pb.setVisibility(View.INVISIBLE);
    }

    @Override
    public void makeToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startAnotherActivity() {
        Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
        startActivity(intent);
    }
}