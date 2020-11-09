package com.cikarastudio.cikarasimetalia.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cikarastudio.cikarasimetalia.R;
import com.cikarastudio.cikarasimetalia.model.KunjunganBaruModel;

import java.util.ArrayList;

public class MyKunjunganBaruAdapter extends RecyclerView.Adapter<MyKunjunganBaruAdapter.MyKunjunganBaruViewHolder> {

    private Context mContext;
    private ArrayList<KunjunganBaruModel> mKunjuganBaruList;

    private OnItemClickCallback onItemClickCallback;


    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(KunjunganBaruModel data);
    }

    public MyKunjunganBaruAdapter(Context mContext, ArrayList<KunjunganBaruModel> mKunjuganBaruList) {
        this.mContext = mContext;
        this.mKunjuganBaruList = mKunjuganBaruList;
    }

    @NonNull
    @Override
    public MyKunjunganBaruViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_kunjungan_baru, parent, false);
        return new MyKunjunganBaruAdapter.MyKunjunganBaruViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyKunjunganBaruViewHolder holder, int position) {
        KunjunganBaruModel currentItem = mKunjuganBaruList.get(position);
        String tanggal = currentItem.getTanggal();
        String status = currentItem.getStatus();
        String kunjunganKe = currentItem.getKehamilanKe();

        holder.status.setText(status);
        if ("KRR".equals(status)) {
            holder.status.setBackgroundColor(Color.parseColor("#31CE36"));
            holder.status.setText(status);
        } else if ("KRT".equals(status)) {
            holder.status.setBackgroundColor(Color.parseColor("#FFAD46"));
            holder.status.setText(status);
        } else if ("KRST".equals(status)) {
            holder.status.setBackgroundColor(Color.parseColor("#F1676F"));
            holder.status.setText(status);
        }else {
            holder.status.setBackgroundColor(Color.parseColor("#000000"));
            holder.status.setText("Belum Ada Data");
        }

        holder.tanggal.setText(tanggal);
        holder.kunjunganKe.setText(kunjunganKe);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(mKunjuganBaruList.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mKunjuganBaruList.size();
    }

    public class MyKunjunganBaruViewHolder extends RecyclerView.ViewHolder {
        public TextView tanggal;
        public TextView status;
        public TextView kunjunganKe;

        MyKunjunganBaruViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tgl_pemeriksaan);
            status = itemView.findViewById(R.id.statusKunjunganBaru);
            kunjunganKe = itemView.findViewById(R.id.kehamilanKe);
        }
    }
}
