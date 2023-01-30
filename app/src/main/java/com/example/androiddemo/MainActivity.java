package com.example.androiddemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androiddemo.Activity.BasicUsageActivity;
import com.example.androiddemo.Presenter.IPresenter;
import com.example.androiddemo.Presenter.presenterImpl;
import com.example.androiddemo.SQL.DatabaseHelper;
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
    AppCompatButton mvpRetrofitBtn;
    AppCompatButton listButton;
    AppCompatButton registerButton;
    AppCompatButton databaseButton;
    AppCompatButton basicUsageButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(this, "BookStore.db", null, 2);
        setContentView(R.layout.activity_main);
        mvpButton = findViewById(R.id.mvp_btn);
        mvpRetrofitBtn = findViewById(R.id.mvp_retrofit);
        listButton = findViewById(R.id.list_btn);
        registerButton = findViewById(R.id.mvp_register);
        databaseButton = findViewById(R.id.mvp_database);
        basicUsageButton = findViewById(R.id.mvp_basic);
        initView();

    }


    public void initView() {

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
            mvpButton.showProgress();
            presenter.doLogin(mvpAccount.getText().toString(), mvpPsw.getText().toString());
            Log.e(ContextUtils.TAG, "mvp button clicked");
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

        databaseButton.setOnClickListener(view -> {
            databaseHelper.getWritableDatabase();
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                databaseHelper.addData();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                databaseHelper.dataQuery();
            }).start();
        });

        basicUsageButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BasicUsageActivity.class);
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

    @Override
    public void buttonSubmitSuccess() {
        mvpButton.showSucceed();
    }

    @Override
    public void buttonSubmitFailed() {
        mvpButton.showError();
    }

    @Override
    public void buttonReset() {
        mvpButton.reset();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}