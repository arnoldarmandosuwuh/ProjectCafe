package com.cafe_papsi.aplikasipapsicafe.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cafe_papsi.aplikasipapsicafe.R;
import com.cafe_papsi.aplikasipapsicafe.model.Transaksi;

import java.util.ArrayList;

public class AdapterTransaksi extends RecyclerView.Adapter<AdapterTransaksi.TransaksiViewHolder> {

    private ArrayList<Transaksi> listTransaksi;
    private Context context;

    public AdapterTransaksi(Context context, ArrayList<Transaksi> listTransaksi) {
        this.context = context;
        this.listTransaksi = listTransaksi;
    }

    @NonNull
    @Override
    public TransaksiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaksi, viewGroup, false);
        return new TransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiViewHolder transaksiViewHolder, int i) {
        Transaksi transaksi = listTransaksi.get(i);

        transaksiViewHolder.tvTMeja.setText("Nomor Meja = " + transaksi.getNoMeja());
        transaksiViewHolder.tvTTotal.setText("Total = Rp. " + transaksi.getTotal());
        transaksiViewHolder.tvTDiskon.setText("Diskon = Rp. " + transaksi.getDiskon());
        transaksiViewHolder.tvTglTransaksi.setText("Tanggal Transaksi = " + transaksi.getTglTransaksi());

    }

    @Override
    public int getItemCount() {
        return listTransaksi == null ? 0 : listTransaksi.size();
    }

    class TransaksiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTMeja, tvTTotal, tvTDiskon, tvTglTransaksi;

        public TransaksiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTMeja = itemView.findViewById(R.id.tvTMeja);
            tvTTotal = itemView.findViewById(R.id.tvTTotal);
            tvTDiskon = itemView.findViewById(R.id.tvTDiskon);
            tvTglTransaksi = itemView.findViewById(R.id.tvTglTransaksi);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
