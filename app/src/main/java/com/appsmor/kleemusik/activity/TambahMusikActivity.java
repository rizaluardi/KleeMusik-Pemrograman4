package com.appsmor.kleemusik.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appsmor.kleemusik.R;
import com.appsmor.kleemusik.adapter.MusikAdapter;
import com.appsmor.kleemusik.helper.DBHandler;
import com.appsmor.kleemusik.model.Musik;

import java.util.List;

public class TambahMusikActivity extends AppCompatActivity {

    private EditText et_musik;
    private EditText et_artis;
    private Button button_tambahdata;

    private DBHandler dbHandler;
    private MusikAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_musik);
        dbHandler = new DBHandler(this);
        initComponents();
    }
    private void initComponents(){
        et_musik = (EditText) findViewById(R.id.et_musik);
        et_artis = (EditText) findViewById(R.id.et_artis);
        button_tambahdata = (Button) findViewById(R.id.button_tambahdata);

        button_tambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiForm();
            }
        });
    }
    private void validasiForm(){
        String form_musik = et_musik.getText().toString();
        String form_artis = et_artis.getText().toString();

        if (form_musik.isEmpty()){
            et_musik.setError("Isi musik dulu");
            et_musik.requestFocus();
        } if (form_artis.isEmpty()){
            et_artis.setError("Isi nama artis dulu");
            et_artis.requestFocus();
        } else {
            dbHandler.tambahMusik(new Musik(form_musik, form_artis));
            List<Musik> musikList = dbHandler.getSemuaMusik();
            adapter = new MusikAdapter(musikList);
            adapter.notifyDataSetChanged();

            Toast.makeText(TambahMusikActivity.this, "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show();
        }
    }
}