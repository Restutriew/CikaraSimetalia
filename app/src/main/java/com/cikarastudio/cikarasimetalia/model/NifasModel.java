package com.cikarastudio.cikarasimetalia.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NifasModel implements Parcelable {
    String id_nifas;
    String tanggal_kunjungan;
    String kunjungan_ke;

    public NifasModel(String id_nifas, String tanggal_kunjungan, String kunjungan_ke) {
        this.id_nifas = id_nifas;
        this.tanggal_kunjungan = tanggal_kunjungan;
        this.kunjungan_ke = kunjungan_ke;
    }

    public String getId_nifas() {
        return id_nifas;
    }

    public void setId_nifas(String id_nifas) {
        this.id_nifas = id_nifas;
    }

    public String getTanggal_kunjungan() {
        return tanggal_kunjungan;
    }

    public void setTanggal_kunjungan(String tanggal_kunjungan) {
        this.tanggal_kunjungan = tanggal_kunjungan;
    }

    public String getKunjungan_ke() {
        return kunjungan_ke;
    }

    public void setKunjungan_ke(String kunjungan_ke) {
        this.kunjungan_ke = kunjungan_ke;
    }

    public static Creator<NifasModel> getCREATOR() {
        return CREATOR;
    }

    protected NifasModel(Parcel in) {
        id_nifas = in.readString();
        tanggal_kunjungan = in.readString();
        kunjungan_ke = in.readString();
    }

    public static final Creator<NifasModel> CREATOR = new Creator<NifasModel>() {
        @Override
        public NifasModel createFromParcel(Parcel in) {
            return new NifasModel(in);
        }

        @Override
        public NifasModel[] newArray(int size) {
            return new NifasModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id_nifas);
        parcel.writeString(tanggal_kunjungan);
        parcel.writeString(kunjungan_ke);
    }
}
