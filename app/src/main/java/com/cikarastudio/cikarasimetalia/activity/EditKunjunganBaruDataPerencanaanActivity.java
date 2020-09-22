package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditKunjunganBaruDataPerencanaanActivity extends AppCompatActivity {

    Button btn_updateDataPerencanaan;
    ImageView btn_backDataPerencanaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_baru_data_perencanaan);

        btn_backDataPerencanaan = findViewById(R.id.btn_backEditDataPerencanaan);
        btn_backDataPerencanaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_updateDataPerencanaan = findViewById(R.id.btn_updateDataPerencanaan);
        btn_updateDataPerencanaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}