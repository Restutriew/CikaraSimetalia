package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class TambahDataScreeningActivity extends AppCompatActivity {

    Button btn_updateDataSkrinning;
    ImageView btn_backDataSkrinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_screening);

        btn_backDataSkrinning = findViewById(R.id.btn_backEditDataSkrinning);
        btn_backDataSkrinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_updateDataSkrinning = findViewById(R.id.btn_updateDataSkrinning);
        btn_updateDataSkrinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}