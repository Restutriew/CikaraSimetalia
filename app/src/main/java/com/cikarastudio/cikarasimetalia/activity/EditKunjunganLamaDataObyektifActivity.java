package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditKunjunganLamaDataObyektifActivity extends AppCompatActivity {

    Button btn_updateDataObyektifLama;
    ImageView btn_backDataObyektifLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_lama_data_obyektif);

        btn_backDataObyektifLama = findViewById(R.id.btn_backEditDataObyektifLama);
        btn_backDataObyektifLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_updateDataObyektifLama = findViewById(R.id.btn_updateDataObyektifLama);
        btn_updateDataObyektifLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}