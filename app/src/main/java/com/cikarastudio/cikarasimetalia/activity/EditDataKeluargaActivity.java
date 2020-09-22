package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditDataKeluargaActivity extends AppCompatActivity {

    Button btn_updateDataKeluarga;
    ImageView btn_backKeluarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_keluarga);

        btn_backKeluarga = findViewById(R.id.btn_backEditKeluarga);
        btn_backKeluarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_updateDataKeluarga = findViewById(R.id.btn_updateDataKeluarga);
        btn_updateDataKeluarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}