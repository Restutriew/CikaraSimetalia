package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditKunjunganLamaDataP4KActivity extends AppCompatActivity {

    Button btn_updateP4KLama;
    ImageView btn_backP4KLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_lama_data_p4_k);

        btn_backP4KLama = findViewById(R.id.btn_backEditDataP4KLama);
        btn_backP4KLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_updateP4KLama = findViewById(R.id.btn_updateDataP4KLama);
        btn_updateP4KLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}