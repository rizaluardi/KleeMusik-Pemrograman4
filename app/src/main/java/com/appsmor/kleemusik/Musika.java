package com.appsmor.kleemusik;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.appsmor.kleemusik.activity.MainScreenActivity;


public class Musika extends Fragment {
    private static final String TAG = "Musika";

    ImageButton plBtn,svBtn,dtAse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musika, container, false);

        plBtn = (ImageButton)view.findViewById(R.id.ple);
        svBtn = (ImageButton)view.findViewById(R.id.sav);
        dtAse = (ImageButton)view.findViewById(R.id.datas);

        plBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playbutton = new Intent(getActivity(), PlayMusiknya.class);
                //berfunngsi untuk melakukan eksekusi dari parameter explicit ke activity berikutnya
                startActivity(playbutton);
            }
        });

        svBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent savebutton = new Intent(getActivity(), SimpanMusik.class);
                //berfunngsi untuk melakukan eksekusi dari parameter explicit ke activity berikutnya
                startActivity(savebutton);
            }
        });
        dtAse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent databutton = new Intent(getActivity(), MainScreenActivity.class);
                //berfunngsi untuk melakukan eksekusi dari parameter explicit ke activity berikutnya
                startActivity(databutton);
            }
        });


    return view;
    }
}