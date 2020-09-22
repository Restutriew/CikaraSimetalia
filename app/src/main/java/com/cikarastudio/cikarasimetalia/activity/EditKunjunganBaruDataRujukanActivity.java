package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditKunjunganBaruDataRujukanActivity extends AppCompatActivity {

    Button btn_updateEditRujukan;
    ImageView btn_backEditRujukan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_baru_data_rujukan);

        btn_updateEditRujukan = findViewById(R.id.btn_updateDataRujukan);
        btn_updateEditRujukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_backEditRujukan = findViewById(R.id.btn_backEditDataRujukan);
        btn_backEditRujukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}