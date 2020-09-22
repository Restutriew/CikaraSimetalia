package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class TambahDataKunjunganLamaActivity extends AppCompatActivity {

    Button btn_simpanDataKunjunganLama;
    ImageView btn_backKunjunganLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_kunjungan_lama);

        btn_simpanDataKunjunganLama = findViewById(R.id.btn_simpanDataKunjunganLama);
        btn_simpanDataKunjunganLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_backKunjunganLama = findViewById(R.id.btn_backKunjunganLama);
        btn_backKunjunganLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}