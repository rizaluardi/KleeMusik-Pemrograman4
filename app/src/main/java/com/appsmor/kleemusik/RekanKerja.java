package com.appsmor.kleemusik;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class RekanKerja extends Fragment {

    ListView listView;
    SimpleAdapter adapter;
    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> mylist;
    String[] nm;
    String[] npm;
    String[] img;
    private static final String TAG = "RekanKerja";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rekan_kerja, container, false);

        listView = (ListView) view.findViewById(R.id.list_view);
        nm = new String[] {
                "Rizaluardi Achmad Pratama","Hanif Wavian P","Zanwar Arif","Irfan Hernandez"
        };
        npm = new String[]{
                "1184102","1184058","1184050","1184014"
        };
        img = new String[]{
                Integer.toString(R.drawable.izal),Integer.toString(R.drawable.hanif),Integer.toString(R.drawable.arip),Integer.toString(R.drawable.nandes)
        };
        mylist = new ArrayList<HashMap<String, String>>();

        for (int i=0; i<nm.length; i++){
            map = new HashMap<String, String>();
            map.put("nama", nm[i]);
            map.put("Npm", npm[i]);
            map.put("Gambar", img[i]);
            mylist.add(map);
        }
        adapter = new SimpleAdapter(getActivity(), mylist, R.layout.profil_data, new String[]{"nama", "Npm", "Gambar"}, new int[]{R.id.txt_nama,(R.id.txt_KelasNpm),(R.id.img)});
        listView.setAdapter(adapter);


        return view;
    }
}