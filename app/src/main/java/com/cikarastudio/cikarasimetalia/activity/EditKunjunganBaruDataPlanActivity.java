package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class EditKunjunganBaruDataPlanActivity extends AppCompatActivity {

    Button btn_updateDataPlan;
    ImageView btn_backDataPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kunjungan_baru_data_plan);

        btn_backDataPlan = findViewById(R.id.btn_backEditDataPlan);
        btn_backDataPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_updateDataPlan = findViewById(R.id.btn_updateDataPlan);
        btn_updateDataPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}