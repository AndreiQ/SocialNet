package com.example.andrei.SocialNet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import siteLocal.AbstractPostHandler;
import siteLocal.IData;
import siteLocal.User;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG_LoginActivity = "LoginActivity";
    private TextView mEmailView;
    private TextView mPasswordView;
    private Button loginButton;
    private String email;
    private String password;
    private Button registerButton;
    private TextView loginError;
    private User AttemptResult;
    private  boolean waitingForServerResponse = true;

    private View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (attemptLogin())
                {
                    loginError.setVisibility(loginError.INVISIBLE);

                    Intent goToMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                  //  goToMainActivity.putExtra("user",  AttemptResult);
                    startActivity(goToMainActivity);
                }
             else

                loginError.setVisibility(loginError.VISIBLE);
        }
    };

    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent goToRegisterActivity = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(goToRegisterActivity);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (EditText) findViewById(R.id.emailText);
        mPasswordView = (EditText) findViewById(R.id.passwordText);

        loginError = (TextView) findViewById(R.id.textLoginFailed);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(registerListener);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(loginListener);
    }

    public boolean attemptLogin() {

        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();

        UserLoginTask mAuthTask = new UserLoginTask(email, password);
        mAuthTask.execute((Void)null);


        if(AttemptResult != null)
                return true;
            else
                return false;
    }

    public class UserLoginTask extends AbstractPostHandler {
       UserLoginTask(String email, String password) {
            super();
            arguments.put("email",email);
            arguments.put("password",password);

        }

        @Override
        protected IData getModelInstance() {
            return new User();
        }

        @Override
        protected String getServerResources() {
            return "/User/Login.php";
        }

        @Override
        protected void onPostExecute(IData result) {

            LoginActivity.this.setLoginAttemptResult((User)result);
            Log.d(TAG_LoginActivity," aici: " + result.toString());
            waitingForServerResponse = false;
        }

    }

    private void setLoginAttemptResult(User result) {
        this.AttemptResult = result;
    }
}


