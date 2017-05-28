package com.example.andrei.SocialNet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.andrei.SocialNet.Adapters.UserAdapter;

import java.util.ArrayList;
import java.util.List;

import siteLocal.User;

public class FindFriendsFragment extends Fragment {
    public FindFriendsFragment() {
        // Required empty public constructor
    }


    private Fragment myFragment;
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_friends, container, false);
        // Construct the data source
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
// Create the adapter to convert the array to views
        UserAdapter adapter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            adapter = new UserAdapter(getContext(), arrayOfUsers);
        }
// Attach the adapter to a ListView
        ListView listView = (ListView) view.findViewById(R.id.friends);
        listView.setAdapter(adapter);

        // Add item to adapter
        User newUser = new User("Nathan", "San Diego","87798","7887y78");
        adapter.add(newUser);

        User newUser2 = new User("Nathan", "San Diego","87798","7887y78");
        adapter.add(newUser2);

        User newUser3 = new User("Nathan", "San Diego","87798","7887y78");
        adapter.add(newUser3);

        User newUser5 = new User("Nathan", "San Diego","87798","7887y78");
        adapter.add(newUser5);



        return view;
    }
}
