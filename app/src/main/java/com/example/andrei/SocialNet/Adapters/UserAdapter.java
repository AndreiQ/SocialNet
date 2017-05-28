package com.example.andrei.SocialNet.Adapters;

import android.app.Activity;
import android.content.Context;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.net.sip.SipAudioCall;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.andrei.SocialNet.FindFriendsFragment;
import com.example.andrei.SocialNet.MainActivity;
import com.example.andrei.SocialNet.MessagesFragment;
import com.example.andrei.SocialNet.R;

import java.util.ArrayList;

import siteLocal.User;

/**
 * Created by Andrei on 5/27/2017.
 */


public class UserAdapter extends ArrayAdapter<User> {

    private Fragment myFragment;
    private FragmentManager fragmentManager;
    private Context cont;

    public UserAdapter(Context context, ArrayList<User> users) {

        super(context, 0, users);
        cont = context;
    }
    private View.OnClickListener chatListener = new View.OnClickListener() {


        @Override
        public void onClick(View view) {



            FragmentManager fm = ((Activity) getContext()).getFragmentManager();

           myFragment = new MessagesFragment();
            android.app.FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_container, myFragment).commit();



        }};

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }

        Button btButton = (Button) convertView.findViewById(R.id.user_chat);
        // Cache row position inside the button using `setTag`
       btButton.setTag(position);
        // Attach the click event handler
      btButton.setOnClickListener(chatListener);
        // Lookup view for data population
        TextView userName = (TextView) convertView.findViewById(R.id.user_Name);
        // Populate the data into the template view using the data object
        userName.setText(user.getFirstName()+" "+user.getLastName());
        // Return the completed view to render on screen
        return convertView;
    }
}