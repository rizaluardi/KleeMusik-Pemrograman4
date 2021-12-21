package com.appsmor.kleemusik.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Button;

import com.appsmor.kleemusik.MainActivity;
import com.appsmor.kleemusik.R;
import com.appsmor.kleemusik.helper.DBHandler;

public class MainScreenActivity extends AppCompatActivity {

    private Button button_tambahdata;
    private Button button_lihatdata;
    private Button button_hapusdata;
    private Button bckact;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        dbHandler = new DBHandler(MainScreenActivity.this);
        initComponents();
    }
    private void initComponents(){
        button_tambahdata = (Button) findViewById(R.id.button_tambahdata);
        button_lihatdata = (Button) findViewById(R.id.button_lihatdata);
        button_hapusdata = (Button) findViewById(R.id.button_hapusdata);
        bckact = (Button) findViewById(R.id.backact);


        button_tambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreenActivity.this, TambahMusikActivity.class));
            }
        });

        button_lihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreenActivity.this, LihatMusikActivity.class));
            }
        });

        button_hapusdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.hapusSemuaDataMusik();
                Toast.makeText(MainScreenActivity.this, "Berhasil Menghapus Semua Data Mahasiswa", Toast.LENGTH_SHORT).show();
            }
        });
        bckact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreenActivity.this, MainActivity.class));
            }
        });
    }
}
