package com.example.andrei.SocialNet;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {


    private Fragment myFragment;
    private FragmentManager fragmentManager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news_feed:
                    myFragment = new NewsFeedFragment();
                    break;
                case R.id.navigation_profile:
                    myFragment = new ProfileFragment();
                    break;
                case R.id.navigation_messages:
                    myFragment = new FindFriendsFragment();
                    break;
                default:
                    myFragment = new NewsFeedFragment();
                    break;
            }

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_container, myFragment).commit();


            return true;
        }

    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle dataFromLoginActivity = getIntent().getExtras();
        String userAsString = dataFromLoginActivity.getString("userAsString");


/*
        Bundle dataFromRegisterActivity = getIntent().getExtras();
        String data = dataFromRegisterActivity.getString("data");
        mTextMessage.setText(data);
*/

        fragmentManager = getFragmentManager();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
