package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditKunjunganBaruDataSubyektifActivity extends AppCompatActivity {

    Button btn_updateDataSubyektif;
    ImageView btn_backDataSubyektif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_baru_data_subyektif);

        btn_backDataSubyektif = findViewById(R.id.btn_backEditDataSubyektif);
        btn_backDataSubyektif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_updateDataSubyektif = findViewById(R.id.btn_updateDataSubyektif);
        btn_updateDataSubyektif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}