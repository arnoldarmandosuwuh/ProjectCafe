package com.cafe_papsi.aplikasipapsicafe.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cafe_papsi.aplikasipapsicafe.R;
import com.cafe_papsi.aplikasipapsicafe.model.Transaksi;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
        NumberFormat formatter = new DecimalFormat("#,###");

        int totalHarga = Integer.valueOf(transaksi.getTotal());

        transaksiViewHolder.tvTMeja.setText("Nomor Meja : " + transaksi.getNoMeja());
        transaksiViewHolder.tvTTotal.setText("Total : Rp." + formatter.format(totalHarga));
        transaksiViewHolder.tvTglTransaksi.setText("Tanggal : " + transaksi.getTglTransaksi());

    }

    @Override
    public int getItemCount() {
        return listTransaksi == null ? 0 : listTransaksi.size();
    }

    class TransaksiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTMeja, tvTTotal, tvTglTransaksi;

        public TransaksiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTMeja = itemView.findViewById(R.id.tvTMeja);
            tvTTotal = itemView.findViewById(R.id.tvTTotal);
            tvTglTransaksi = itemView.findViewById(R.id.tvTglTransaksi);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Transaksi : \n" + tvTMeja.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
