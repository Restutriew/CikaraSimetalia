package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditKunjunganBaruDataAnalisisActivity extends AppCompatActivity {

    Button btn_updateDataAnalisis;
    ImageView btn_backDataAnalisis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_baru_data_analisis);

        btn_updateDataAnalisis = findViewById(R.id.btn_updateDataAnalisis);
        btn_updateDataAnalisis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_backDataAnalisis = findViewById(R.id.btn_backEditDataAnalisis);
        btn_backDataAnalisis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}