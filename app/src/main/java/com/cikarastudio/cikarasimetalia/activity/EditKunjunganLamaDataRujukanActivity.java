package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditKunjunganLamaDataRujukanActivity extends AppCompatActivity {

    Button btn_updateRujukanLama;
    ImageView btn_backRujukanLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_lama_data_rujukan);

        btn_backRujukanLama = findViewById(R.id.btn_backEditDataRujukanLama);
        btn_backRujukanLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_updateRujukanLama = findViewById(R.id.btn_updateDataRujukanLama);
        btn_updateRujukanLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}