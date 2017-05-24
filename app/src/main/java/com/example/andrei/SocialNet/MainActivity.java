package com.example.andrei.SocialNet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.andrei.SocialNet.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements PostsFragment.OnListFragmentInteractionListener {


    private Fragment fragment;
    private FragmentManager fragmentManager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news_feed:
                    fragment = new NewsFeedFragment();
                    break;
                case R.id.navigation_profile:
                    fragment = new ProfileFragment();
                    break;
                case R.id.navigation_messages:
                    fragment = new MessagesFragment();
                    break;
            }

            final android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_container, fragment).commit();


            return true;
        }

    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mTextMessage = (TextView) findViewById(R.id.message);

      // Bundle dataFromLoginActivity = getIntent().getExtras();
     //   String userAsString = dataFromLoginActivity.getString("userAsString");
    //    mTextMessage.setText(userAsString);

/*
        Bundle dataFromRegisterActivity = getIntent().getExtras();
        String data = dataFromRegisterActivity.getString("data");
        mTextMessage.setText(data);
*/

        fragmentManager = getSupportFragmentManager();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
