package com.cikarastudio.cikarasimetalia.model;

import android.os.Parcel;
import android.os.Parcelable;

public class KunjunganBaruModel implements Parcelable {
    String id_kunjunganBaru;
    String tanggal;
    String status;
    String kehamilanKe;

    public KunjunganBaruModel(String id_kunjunganBaru, String tanggal, String status, String kehamilanKe) {
        this.id_kunjunganBaru = id_kunjunganBaru;
        this.tanggal = tanggal;
        this.status = status;
        this.kehamilanKe = kehamilanKe;
    }

    public String getId_kunjunganBaru() {
        return id_kunjunganBaru;
    }

    public void setId_kunjunganBaru(String id_kunjunganBaru) {
        this.id_kunjunganBaru = id_kunjunganBaru;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getKehamilanKe() {
        return kehamilanKe;
    }

    public void setKehamilanKe(String kehamilanKe) {
        this.kehamilanKe = kehamilanKe;
    }

    public static Creator<KunjunganBaruModel> getCREATOR() {
        return CREATOR;
    }

    protected KunjunganBaruModel(Parcel in) {
        id_kunjunganBaru = in.readString();
        tanggal = in.readString();
        status = in.readString();
        kehamilanKe = in.readString();
    }

    public static final Creator<KunjunganBaruModel> CREATOR = new Creator<KunjunganBaruModel>() {
        @Override
        public KunjunganBaruModel createFromParcel(Parcel in) {
            return new KunjunganBaruModel(in);
        }

        @Override
        public KunjunganBaruModel[] newArray(int size) {
            return new KunjunganBaruModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id_kunjunganBaru);
        parcel.writeString(tanggal);
        parcel.writeString(status);
        parcel.writeString(kehamilanKe);
    }
}

