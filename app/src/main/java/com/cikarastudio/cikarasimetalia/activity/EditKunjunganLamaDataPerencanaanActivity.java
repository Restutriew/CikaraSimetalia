package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditKunjunganLamaDataPerencanaanActivity extends AppCompatActivity {

    Button btn_updatePerencanaanLama;
    ImageView btn_backPerencanaanLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_lama_data_perencanaan);

        btn_backPerencanaanLama = findViewById(R.id.btn_backEditDataPerencanaanLama);
        btn_backPerencanaanLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_updatePerencanaanLama = findViewById(R.id.btn_updateDataPerencanaanLama);
        btn_updatePerencanaanLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}