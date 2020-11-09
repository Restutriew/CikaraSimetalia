package com.cikarastudio.cikarasimetalia.model;

import android.os.Parcel;
import android.os.Parcelable;

public class KunjunganLamaModel implements Parcelable {
    String id_kunjunganLama;
    String tanggal;
    String keluhan;

    public KunjunganLamaModel(String id_kunjunganLama, String tanggal, String keluhan) {
        this.id_kunjunganLama = id_kunjunganLama;
        this.tanggal = tanggal;
        this.keluhan = keluhan;
    }

    public String getId_kunjunganLama() {
        return id_kunjunganLama;
    }

    public void setId_kunjunganLama(String id_kunjunganLama) {
        this.id_kunjunganLama = id_kunjunganLama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public static Creator<KunjunganLamaModel> getCREATOR() {
        return CREATOR;
    }

    protected KunjunganLamaModel(Parcel in) {
        id_kunjunganLama = in.readString();
        tanggal = in.readString();
        keluhan = in.readString();
    }

    public static final Creator<KunjunganLamaModel> CREATOR = new Creator<KunjunganLamaModel>() {
        @Override
        public KunjunganLamaModel createFromParcel(Parcel in) {
            return new KunjunganLamaModel(in);
        }

        @Override
        public KunjunganLamaModel[] newArray(int size) {
            return new KunjunganLamaModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id_kunjunganLama);
        parcel.writeString(tanggal);
        parcel.writeString(keluhan);
    }
}
