package com.example.andrei.SocialNet;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    private TextView mUserView;


    public ProfileFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Bundle b = getActivity().getIntent().getExtras();

        String userAsString = b.getString("userAsString");
       //mUserView = (TextView) view.findViewById(R.id.profile);
  //  mUserView.setText(userAsString);

        return view;
    }


}
