package com.example.andrei.SocialNet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import siteLocal.AbstractData;
import siteLocal.AbstractPostHandler;
import siteLocal.IData;
import siteLocal.User;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG_LoginActivity = "LoginActivity";

    private TextView mEmailView;
    private TextView mPasswordView;
    private TextView loginError;
    private User AttemptResult = new User();

    private Button.OnClickListener loginListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
           String email = mEmailView.getText().toString();
           String password = mPasswordView.getText().toString();

            if(email.isEmpty() || password.isEmpty()) {
                loginError.setVisibility(loginError.VISIBLE);
                return;
            }

            UserLoginTask mAuthTask = new UserLoginTask(email, password);
            try {
                AttemptResult = (User)mAuthTask.execute((Void)null).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if (AttemptResult.getEmail() == null)
                {
                    loginError.setVisibility(loginError.VISIBLE);
                }
             else{
                loginError.setVisibility(loginError.INVISIBLE);
                Intent goToMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                goToMainActivity.putExtra("userAsString",AttemptResult.toString());
                startActivity(goToMainActivity);
            }
        }
    };

    private Button.OnClickListener registerListener = new Button.OnClickListener() {
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

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(registerListener);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(loginListener);
    }


    public class UserLoginTask extends AbstractPostHandler {
       UserLoginTask(String email, String password) {
            super();
            arguments.put("email",email);
            arguments.put("password",password);

        }

        @Override
        protected AbstractData getModelInstance() {
            return new User();
        }

        @Override
        protected String getServerResources() {
            return "/User/Login.php";
        }


        @Override
        protected void onPostExecute(AbstractData result) {
            LoginActivity.this.setLoginAttemptResult((User)result);
        }
    }

    private void setLoginAttemptResult(User result) {
        this.AttemptResult = result;
    }
}


