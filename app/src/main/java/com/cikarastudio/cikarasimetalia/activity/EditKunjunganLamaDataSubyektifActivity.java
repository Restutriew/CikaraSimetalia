package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditKunjunganLamaDataSubyektifActivity extends AppCompatActivity {

    Button btn_updateDataSubyektifLama;
    ImageView btn_backDataSubyektifLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_lama_data_subyektif);

        btn_backDataSubyektifLama = findViewById(R.id.btn_backEditDataSubyektifLama);
        btn_backDataSubyektifLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_updateDataSubyektifLama = findViewById(R.id.btn_updateDataSubyektifLama);
        btn_updateDataSubyektifLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}