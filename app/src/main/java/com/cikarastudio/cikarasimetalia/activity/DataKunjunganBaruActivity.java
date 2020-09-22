package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cikarastudio.cikarasimetalia.R;

public class DataKunjunganBaruActivity extends AppCompatActivity {

    ImageView btn_back;
    Button btn_keBeranda, btn_tambahDataKunjungan,btn_keDataSkrinning;
    LinearLayout sementara2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kunjungan_baru);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_keBeranda = findViewById(R.id.btn_keBeranda);
        btn_keBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keBeranda = new Intent(DataKunjunganBaruActivity.this, MainActivity.class);
                startActivity(keBeranda);
                finishAffinity();
            }
        });

        btn_tambahDataKunjungan = findViewById(R.id.btn_tambahDataKunjungan);
        btn_tambahDataKunjungan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ketambahdatakunjungan = new Intent(DataKunjunganBaruActivity.this, TambahDataKunjunganBaruActivity.class);
                startActivity(ketambahdatakunjungan);
            }
        });

        sementara2 = findViewById(R.id.sementara2);
        sementara2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keDetailKunjunganBaru = new Intent(DataKunjunganBaruActivity.this, DetailKunjunganBaruActivity.class);
                startActivity(keDetailKunjunganBaru);
            }
        });

        btn_keDataSkrinning = findViewById(R.id.btn_keDataSkrinning);
        btn_keDataSkrinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keSkrinning = new Intent(DataKunjunganBaruActivity.this,TambahDataScreeningActivity.class);
                startActivity(keSkrinning);
            }
        });
    }
}