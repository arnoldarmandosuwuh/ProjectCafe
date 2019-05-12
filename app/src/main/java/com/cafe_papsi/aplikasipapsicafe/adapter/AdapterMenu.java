package com.cafe_papsi.aplikasipapsicafe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cafe_papsi.aplikasipapsicafe.R;
import com.cafe_papsi.aplikasipapsicafe.model.Menu;
import com.cafe_papsi.aplikasipapsicafe.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.MenuViewHolder> {

    private ArrayList<Menu> listMenu;
    private Context context;

    public AdapterMenu(Context context, ArrayList<Menu> listMenu) {
        this.context = context;
        this.listMenu = listMenu;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, viewGroup, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMenu.MenuViewHolder menuViewHolder, int i) {
        Menu menu = listMenu.get(i);
        NumberFormat formatter = new DecimalFormat("#,###");

        int hargaMenu = Integer.valueOf(menu.getHargaMenu());

        menuViewHolder.tvNamaMenu.setText(menu.getNamaMenu());
        menuViewHolder.tvHargaMenu.setText("Rp. " + formatter.format(hargaMenu));

        if(!menu.getLinkGambar().trim().isEmpty()){
            Picasso.get().load(menu.getLinkGambar()).transform(new CircleTransform()).into(menuViewHolder.ivMenu);
        }
        else {
            Picasso.get().load(R.drawable.no_image).transform(new CircleTransform()).into(menuViewHolder.ivMenu);
        }
    }

    @Override
    public int getItemCount() {
        return listMenu == null ? 0 : listMenu.size();
    }
    class MenuViewHolder extends ViewHolder implements View.OnClickListener {

        ImageView ivMenu;
        TextView tvNamaMenu, tvHargaMenu;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            ivMenu = itemView.findViewById(R.id.ivGambarMenu);
            tvNamaMenu = itemView.findViewById(R.id.tvNamaMenu);
            tvHargaMenu = itemView.findViewById(R.id.tvHargaMenu);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
