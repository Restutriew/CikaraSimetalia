package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditIbuHamilActivity extends AppCompatActivity {

    Button updateDataBumil;
    ImageView btn_backEditBumil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ibu_hamil);

        updateDataBumil = findViewById(R.id.btn_updateDataBumil);
        updateDataBumil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_backEditBumil = findViewById(R.id.btn_backEditBumil);
        btn_backEditBumil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}