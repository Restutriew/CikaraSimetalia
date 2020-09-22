package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cikarastudio.cikarasimetalia.R;

public class DataKunjunganLamaActivity extends AppCompatActivity {

    Button btn_tambahKunjunganLama, btn_keBerandaLama;
    LinearLayout sementara3;
    ImageView btn_backKunjunganLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kunjungan_lama);

        btn_backKunjunganLama = findViewById(R.id.btn_backKunjunganLama);
        btn_backKunjunganLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btn_tambahKunjunganLama = findViewById(R.id.btn_tambahDataKunjunganLama);
        btn_tambahKunjunganLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keTambahKunjunganLama = new Intent(DataKunjunganLamaActivity.this, TambahDataKunjunganLamaActivity.class);
                startActivity(keTambahKunjunganLama);
                ;
            }
        });

        sementara3 = findViewById(R.id.sementara3);
        sementara3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keDetailKunjunganLama = new Intent(DataKunjunganLamaActivity.this,DetailKunjunganLamaActivity.class);
                startActivity(keDetailKunjunganLama);
            }
        });

        btn_keBerandaLama = findViewById(R.id.btn_keBerandaLama);
        btn_keBerandaLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keBerandaLama = new Intent(DataKunjunganLamaActivity.this, MainActivity.class);
                startActivity(keBerandaLama);
                finishAffinity();
            }
        });
    }
}