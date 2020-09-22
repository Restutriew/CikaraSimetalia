package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditKunjunganBaruDataP4KActivity extends AppCompatActivity {

    Button btn_UpdateEditP4K;
    ImageView btn_backEditP4K;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_baru_data_p4_k);

        btn_backEditP4K = findViewById(R.id.btn_backEditDataP4K);
        btn_backEditP4K.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_UpdateEditP4K = findViewById(R.id.btn_updateDataP4K);
        btn_UpdateEditP4K.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}