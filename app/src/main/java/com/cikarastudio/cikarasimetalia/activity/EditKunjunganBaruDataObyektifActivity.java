package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditKunjunganBaruDataObyektifActivity extends AppCompatActivity {

    Button btn_updateDataObyektif;
    ImageView btn_backDataObyektif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_baru_data_obyektif);

        btn_updateDataObyektif = findViewById(R.id.btn_updateDataObyektif);
        btn_updateDataObyektif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_backDataObyektif = findViewById(R.id.btn_backEditDataObyektif);
        btn_backDataObyektif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}