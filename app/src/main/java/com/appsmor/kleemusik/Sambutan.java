package com.appsmor.kleemusik;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


public class Sambutan extends Fragment {
    TextView textView_email;
    Button logouts;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL ="email";
    private static final String DROP="com.appsmor.kleemusik";

    private static final String TAG = "Sambutan";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sambutan, container, false);

        textView_email = view.findViewById(R.id.text_mail);
        logouts = view.findViewById(R.id.logouts);

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        SharedPreferences prefs = this.getActivity().getSharedPreferences(DROP, Context.MODE_PRIVATE);

        String email = sharedPreferences.getString(KEY_EMAIL,null);

        if (email != null){
            textView_email.setText("Email anda " +email);
        }

        logouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                SharedPreferences.Editor editer = prefs.edit();
                editor.clear();
                editor.commit();
                editer.clear();
                editer.commit();
                Toast.makeText(getActivity(), "logout berhasil", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),LoginAct.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    return view;
    }
}