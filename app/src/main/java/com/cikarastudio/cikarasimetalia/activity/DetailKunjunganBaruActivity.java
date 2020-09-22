package com.cikarastudio.cikarasimetalia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cikarastudio.cikarasimetalia.R;

public class DetailKunjunganBaruActivity extends AppCompatActivity {

    Button btn_hapusDetailKunjunganBaru,btn_keBeranda, btn_keKunjunganLama;
    ImageView btn_backDetailKunjunganBaru;
    CardView cr_detailSubyektif, cr_detailObyektif, cr_detailAnalisis, cr_detailPlan,
            cr_detailPerencanaan, cr_detailP4K, cr_detailRujukan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kunjungan_baru);

        btn_backDetailKunjunganBaru = findViewById(R.id.btn_backDetailKunjunganBaru);
        btn_backDetailKunjunganBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_hapusDetailKunjunganBaru = findViewById(R.id.btn_hapusDetailKunjunganBaru);
        btn_hapusDetailKunjunganBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cr_detailSubyektif = findViewById(R.id.cr_detailSubyektif);
        cr_detailSubyektif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keEditSubyektif = new Intent(DetailKunjunganBaruActivity.this, EditKunjunganBaruDataSubyektifActivity.class);
                startActivity(keEditSubyektif);
            }
        });

        cr_detailObyektif = findViewById(R.id.cr_detailObyektif1);
        cr_detailObyektif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keEditObyektif = new Intent(DetailKunjunganBaruActivity.this, EditKunjunganBaruDataObyektifActivity.class);
                startActivity(keEditObyektif);
            }
        });

        cr_detailAnalisis = findViewById(R.id.cr_detailAnalisis);
        cr_detailAnalisis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keEditAnalisis = new Intent(DetailKunjunganBaruActivity.this, EditKunjunganBaruDataAnalisisActivity.class);
                startActivity(keEditAnalisis);
            }
        });

        cr_detailPlan = findViewById(R.id.cr_detailPlan);
        cr_detailPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keEditPlan = new Intent(DetailKunjunganBaruActivity.this, EditKunjunganBaruDataPlanActivity.class);
                startActivity(keEditPlan);
            }
        });


        cr_detailPerencanaan = findViewById(R.id.cr_perencanaanKehamilan);
        cr_detailPerencanaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keEditPerencanaan = new Intent(DetailKunjunganBaruActivity.this, EditKunjunganBaruDataPerencanaanActivity.class);
                startActivity(keEditPerencanaan);
            }
        });

        cr_detailP4K = findViewById(R.id.cr_P4k);
        cr_detailP4K.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keeditP4K = new Intent(DetailKunjunganBaruActivity.this, EditKunjunganBaruDataP4KActivity.class);
                startActivity(keeditP4K);
            }
        });

        cr_detailRujukan = findViewById(R.id.cr_rujukan);
        cr_detailRujukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keDetailRUjukan = new Intent(DetailKunjunganBaruActivity.this, EditKunjunganBaruDataRujukanActivity.class);
                startActivity(keDetailRUjukan);
            }
        });

        btn_keBeranda = findViewById(R.id.btn_keBeranda);
        btn_keBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keBeranda = new Intent(DetailKunjunganBaruActivity.this,MainActivity.class);
                startActivity(keBeranda);
                finishAffinity();
            }
        });


        btn_keKunjunganLama = findViewById(R.id.btn_keKunjunganLama);
        btn_keKunjunganLama. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keKunjunganLama = new Intent (DetailKunjunganBaruActivity.this, DataKunjunganLamaActivity.class);
                startActivity(keKunjunganLama);
            }
        });
    }
}