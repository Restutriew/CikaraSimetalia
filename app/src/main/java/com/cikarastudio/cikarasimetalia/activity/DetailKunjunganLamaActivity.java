package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class DetailKunjunganLamaActivity extends AppCompatActivity {

    ImageView btn_backKunjunganLama;
    Button btn_hapusDataKunjunganLama, btn_keBeranda;
    CardView cr_subyektifLama, cr_obyektifLama, cr_analisisLama, cr_planLama, cr_perencanaanLama, cr_p4kLama, cr_rujukanLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kunjungan_lama);

        btn_backKunjunganLama = findViewById(R.id.btn_backDetailKunjunganLama);
        btn_backKunjunganLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_hapusDataKunjunganLama = findViewById(R.id.btn_hapusDetailKunjunganLama);
        btn_hapusDataKunjunganLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_keBeranda = findViewById(R.id.btn_keBerandaLama);
        btn_keBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keBeranda = new Intent(DetailKunjunganLamaActivity.this, MainActivity.class);
                startActivity(keBeranda);
                finishAffinity();
            }
        });

        cr_subyektifLama = findViewById(R.id.cr_detailSubyektifLama);
        cr_subyektifLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keSubyektifLama = new Intent(DetailKunjunganLamaActivity.this, EditKunjunganLamaDataSubyektifActivity.class);
                startActivity(keSubyektifLama);
            }
        });
        cr_obyektifLama = findViewById(R.id.cr_detailObyektifLama);
        cr_obyektifLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keObyektifLama = new Intent(DetailKunjunganLamaActivity.this, EditKunjunganLamaDataObyektifActivity.class);
                startActivity(keObyektifLama);
            }
        });
        cr_analisisLama = findViewById(R.id.cr_detailAnalisisLama);
        cr_analisisLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keAnalisisLama = new Intent(DetailKunjunganLamaActivity.this, EditKunjunganLamaDataAnalisisActivity.class);
                startActivity(keAnalisisLama);
            }
        });
        cr_planLama = findViewById(R.id.cr_detailPlanLama);
        cr_planLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kePlanLama = new Intent(DetailKunjunganLamaActivity.this, EditKunjunganLamaDataPlanActivity.class);
                startActivity(kePlanLama);
            }
        });
        cr_perencanaanLama = findViewById(R.id.cr_perencanaanKehamilanLama);
        cr_perencanaanLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kePerencanaanLama = new Intent(DetailKunjunganLamaActivity.this, EditKunjunganLamaDataPerencanaanActivity.class);
                startActivity(kePerencanaanLama);
            }
        });
        cr_p4kLama = findViewById(R.id.cr_P4kLama);
        cr_p4kLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kep4kLama = new Intent(DetailKunjunganLamaActivity.this, EditKunjunganLamaDataP4KActivity.class);
                startActivity(kep4kLama);
            }
        });
        cr_rujukanLama = findViewById(R.id.cr_rujukanLama);
        cr_rujukanLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kerujukanLama = new Intent(DetailKunjunganLamaActivity.this, EditKunjunganLamaDataRujukanActivity.class);
                startActivity(kerujukanLama);
            }
        });

    }
}