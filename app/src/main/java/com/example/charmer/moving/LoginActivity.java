package com.example.charmer.moving;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charmer.moving.contantData.EditTextClearTools;
import com.example.charmer.moving.contantData.HttpUtils;
import com.example.charmer.moving.pojo.ListActivityBean;
import com.example.charmer.moving.pojo.LoginInfo;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    public static String hostmac;            //本机MAC
    @InjectView(R.id.input_mobile)
    EditText _mobileText;
    @InjectView(R.id.del_phonenumber)
    ImageView _del_phonenumber;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.del_password)
    ImageView _del_password;
    @InjectView(R.id.btn_login)
    AppCompatButton _loginButton;
    @InjectView(R.id.link_forgetpsd)
    TextView _forgetpsdLink;
    @InjectView(R.id.link_signup)
    TextView _signupLink;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        EditTextClearTools.addclerListener(_mobileText, _del_phonenumber);
        EditTextClearTools.addclerListener(_passwordText, _del_password);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        _forgetpsdLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgetPsdActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("正在登录...");
        progressDialog.show();

        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        LoginInfo loginInfo= new LoginInfo(mobile,password);
        Login(loginInfo);
        SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("mobile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("number", mobile);
        editor.commit();
        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "登录失败", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();

        if (mobile.isEmpty() || mobile.length() != 11) {
            // _mobileText.setError("请输入有效的手机号");
            valid = false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            // _passwordText.setError("请输入4-10个字母或数字");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
    private void Login(LoginInfo loginInfo) {

        RequestParams params = new RequestParams(HttpUtils.host+"");
        Gson gson =new Gson();
        String login = gson.toJson(loginInfo);
        params.addQueryStringParameter("page",login);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ListActivityBean bean=gson.fromJson(result, ListActivityBean.class);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

}
