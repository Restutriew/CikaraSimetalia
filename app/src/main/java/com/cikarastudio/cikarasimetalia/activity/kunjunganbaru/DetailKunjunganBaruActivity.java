package com.cikarastudio.cikarasimetalia.activity.kunjunganbaru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.activity.bayi.TambahDataBayiActivity;
import com.cikarastudio.cikarasimetalia.activity.bumil.DetailBumilActivity;
import com.cikarastudio.cikarasimetalia.activity.bumil.EditIbuHamilActivity;
import com.cikarastudio.cikarasimetalia.activity.kehamilan.TambahDataPersalinanActivity;
import com.cikarastudio.cikarasimetalia.activity.kunjunganlama.DetailKunjunganLamaActivity;
import com.cikarastudio.cikarasimetalia.activity.nifas.DetailDataNifasActivity;
import com.cikarastudio.cikarasimetalia.activity.skrinning.TambahDataScreeningActivity;
import com.cikarastudio.cikarasimetalia.model.Bumil;
import com.cikarastudio.cikarasimetalia.model.KunjunganBaruModel;

public class DetailKunjunganBaruActivity extends AppCompatActivity {

    public static final String KUNJUNGANBARU_DATA = "extra_data";
    CardView cr_detailSubyektifBaru, cr_detailObyektifBaru, cr_detailAnalisisBaru,
            cr_detailPlanBaru, cr_detailP4KBaru, cr_detailRujukanBaru;
    String id_kunjunganBaruget;
    LinearLayout keKunjunganLama, keSkrinning, kePersalinan, keBayi, keNifas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kunjungan_baru);

        KunjunganBaruModel KunjunganBaruData = getIntent().getParcelableExtra(KUNJUNGANBARU_DATA);
        final String id_kunjunganBaru = KunjunganBaruData.getId_kunjunganBaru();
        id_kunjunganBaruget = id_kunjunganBaru;


        cr_detailSubyektifBaru = findViewById(R.id.cr_detailSubyektifBaru);
        cr_detailObyektifBaru = findViewById(R.id.cr_detailObyektifBaru);
        cr_detailAnalisisBaru = findViewById(R.id.cr_detailAnalisisBaru);
        cr_detailPlanBaru = findViewById(R.id.cr_detailPlanBaru);
        cr_detailP4KBaru = findViewById(R.id.cr_detailP4KBaru);
        cr_detailRujukanBaru = findViewById(R.id.cr_detailRujukanBaru);
        keKunjunganLama = findViewById(R.id.keKunjunganLama);
        keSkrinning = findViewById(R.id.keSkrinning);
        kePersalinan = findViewById(R.id.kePersalinan);
        keBayi = findViewById(R.id.keBayi);
        keNifas = findViewById(R.id.keNifas);

        cr_detailSubyektifBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keEditSubyektifBaru = new Intent(DetailKunjunganBaruActivity.this, EditKunjunganBaruDataSubyektifActivity.class);
                keEditSubyektifBaru.putExtra(EditKunjunganBaruDataSubyektifActivity.ID_KUNJUNGANBARU, id_kunjunganBaruget);
                startActivity(keEditSubyektifBaru);
            }
        });

        cr_detailObyektifBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keObyektifBaru = new Intent(DetailKunjunganBaruActivity.this, EditKunjunganBaruDataObyektifActivity.class);
                keObyektifBaru.putExtra(EditKunjunganBaruDataObyektifActivity.ID_KUNJUNGANBARU, id_kunjunganBaruget);
                startActivity(keObyektifBaru);
            }
        });

        cr_detailAnalisisBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keAnalisisBaru = new Intent(DetailKunjunganBaruActivity.this, EditKunjunganBaruDataAnalisisActivity.class);
                keAnalisisBaru.putExtra(EditKunjunganBaruDataAnalisisActivity.ID_KUNJUNGANBARU, id_kunjunganBaruget);
                startActivity(keAnalisisBaru);
            }
        });

        cr_detailPlanBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kePlanBaru = new Intent(DetailKunjunganBaruActivity.this, EditKunjunganBaruDataPlanActivity.class);
                kePlanBaru.putExtra(EditKunjunganBaruDataPlanActivity.ID_KUNJUNGANBARU, id_kunjunganBaruget);
                startActivity(kePlanBaru);
            }
        });

        cr_detailP4KBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keP4KBaru = new Intent(DetailKunjunganBaruActivity.this, EditKunjunganBaruDataP4KActivity.class);
                keP4KBaru.putExtra(EditKunjunganBaruDataP4KActivity.ID_KUNJUNGANBARU, id_kunjunganBaruget);
                startActivity(keP4KBaru);
            }
        });

        cr_detailRujukanBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keRujukanBaru = new Intent(DetailKunjunganBaruActivity.this, EditKunjunganBaruDataRujukanActivity.class);
                keRujukanBaru.putExtra(EditKunjunganBaruDataRujukanActivity.ID_KUNJUNGANBARU, id_kunjunganBaruget);
                startActivity(keRujukanBaru);
            }
        });

        keKunjunganLama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keKunjunganLama = new Intent(DetailKunjunganBaruActivity.this, DetailKunjunganLamaActivity.class);
                keKunjunganLama.putExtra(DetailKunjunganLamaActivity.ID_KUNJUNGANBARU, id_kunjunganBaruget);
                startActivity(keKunjunganLama);
            }
        });

        keSkrinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keSkrinning = new Intent(DetailKunjunganBaruActivity.this, TambahDataScreeningActivity.class);
                keSkrinning.putExtra(TambahDataScreeningActivity.ID_KUNJUNGANBARU, id_kunjunganBaruget);
                startActivity(keSkrinning);
            }
        });

        kePersalinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kePersalinan = new Intent(DetailKunjunganBaruActivity.this, TambahDataPersalinanActivity.class);
                kePersalinan.putExtra(TambahDataPersalinanActivity.ID_KUNJUNGANBARU, id_kunjunganBaruget);
                startActivity(kePersalinan);
            }
        });

        keBayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keBayi = new Intent(DetailKunjunganBaruActivity.this, TambahDataBayiActivity.class);
                keBayi.putExtra(TambahDataBayiActivity.ID_KUNJUNGANBARU, id_kunjunganBaruget);
                startActivity(keBayi);
            }
        });

        keNifas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keNifas = new Intent(DetailKunjunganBaruActivity.this, DetailDataNifasActivity.class);
                keNifas.putExtra(DetailDataNifasActivity.ID_KUNJUNGANBARU, id_kunjunganBaruget);
                startActivity(keNifas);
            }

        });
    }
}