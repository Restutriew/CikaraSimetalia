package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditKunjunganLamaDataAnalisisActivity extends AppCompatActivity {

    Button btn_updateDataAnalisisLama;
    ImageView btn_backDataAnalisisLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_lama_data_analisis);

        btn_backDataAnalisisLama = findViewById(R.id.btn_backEditDataAnalisisLama);
        btn_backDataAnalisisLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_updateDataAnalisisLama = findViewById(R.id.btn_updateDataAnalisisLama);
        btn_updateDataAnalisisLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}