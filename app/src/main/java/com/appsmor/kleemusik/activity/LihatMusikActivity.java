package com.appsmor.kleemusik.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.appsmor.kleemusik.R;
import com.appsmor.kleemusik.adapter.MusikAdapter;
import com.appsmor.kleemusik.helper.DBHandler;
import com.appsmor.kleemusik.helper.RecyclerItemClickListener;
import com.appsmor.kleemusik.model.Musik;

import java.util.ArrayList;
import java.util.List;

public class LihatMusikActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MusikAdapter adapter;
    private DBHandler dbHandler;
    private TextView txt_resultadapter;
    private List<Musik> musikList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_musik);
        initComponents();
        initRecyclerView();
        cekDataRecyclerView();
    }
    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.rv_musik);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dbHandler = new DBHandler(LihatMusikActivity.this);
        musikList = dbHandler.getSemuaMusik();
        adapter = new MusikAdapter(musikList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    private void initComponents(){
        txt_resultadapter = (TextView) findViewById(R.id.txt_resultadapter);
    }

    private void cekDataRecyclerView(){
        if (adapter.getItemCount() == 0){
            txt_resultadapter.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            txt_resultadapter.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {
                            // TODO Handle item click
                            Musik msk = musikList.get(position);
                            String musik = msk.getMusik();

                            Toast.makeText(LihatMusikActivity.this, "Klik di " + musik, Toast.LENGTH_SHORT).show();
                        }
                    })
            );
        }
    }
}