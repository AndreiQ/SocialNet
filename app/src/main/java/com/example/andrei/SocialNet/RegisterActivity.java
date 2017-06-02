package com.example.andrei.SocialNet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import siteLocal.AbstractData;
import siteLocal.AbstractPostHandler;
import siteLocal.IData;
import siteLocal.User;

public class RegisterActivity extends AppCompatActivity {


    private static final String TAG_RegisterActivity = "RegisterActivity";
    private TextView mEmailView;
    private TextView mPasswordView;
    private TextView mFirstNameView;
    private TextView mLastNameView;

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private IData userFromServer;
    private Button registerButton;

    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getTextFromViews();

            UserRegisterTask mAuthTask = new UserRegisterTask(email, password,firstName,lastName);
            mAuthTask.execute((Void)null);


            Intent goToMainActivity = new Intent(RegisterActivity.this, MainActivity.class);
            goToMainActivity.putExtra("data", "succes");
            startActivity(goToMainActivity);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = (Button) findViewById(R.id.register_Button);
        registerButton.setOnClickListener(registerListener);

    }
    public void getTextFromViews(){

        mEmailView = (EditText) findViewById(R.id.register_emailText);
        mPasswordView = (EditText) findViewById(R.id.register_passwordText);
        mFirstNameView = (EditText) findViewById(R.id.register_firstNameText);
        mLastNameView = (EditText) findViewById(R.id.register_lastNameText);

        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();
        firstName = mFirstNameView.getText().toString();
        lastName = mLastNameView.getText().toString();

    }

    public void setUserFromServer(IData userFromServer) {
        this.userFromServer = userFromServer;
    }

    public class UserRegisterTask extends AbstractPostHandler {
        UserRegisterTask(String email, String password,String firstName, String lastName) {
            super();
            arguments.put("email",email);
            arguments.put("password",password);
            arguments.put("firstName",firstName);
            arguments.put("lastName",lastName);

        }

        @Override
        protected AbstractData getModelInstance() {
            return new User();
        }

        @Override
        protected String getServerResources() {
            return "/User/Register.php";
        }


        @Override
        protected void onPostExecute(AbstractData result) {

            Log.d(TAG_RegisterActivity," aici register: " + result.toString());
            RegisterActivity.this.setUserFromServer(result);
        }

    }
}
