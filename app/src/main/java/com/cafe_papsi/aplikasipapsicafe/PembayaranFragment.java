package com.cafe_papsi.aplikasipapsicafe;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;




public class PembayaranFragment extends Fragment  {

    private Button btnSubmit;
    private TextView tvDiskon, tvSubTotal, tvHargaNasgor, tvHargaMie, tvHargaSoto, tvHargaGenderuwo, tvHargaPocong, tvHargaKopi, tvBayar;
    private RadioButton rbMember, rbNonMember;
    private EditText etMeja, etJmlNasgor, etJmlMie, etJmlSoto, etJmlGenderuwo, etJmlPocong, etJmlKopi;
    private int nasgor, mie, soto, genderuwo, pocong, kopi, bayar = 0;
    private double diskon = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pembayaran, container, false);

        etMeja = view.findViewById(R.id.etMeja);
        etJmlNasgor = view.findViewById(R.id.etJmlNasgor);
        etJmlMie = view.findViewById(R.id.etJmlMie);
        etJmlSoto = view.findViewById(R.id.etJmlSoto);
        etJmlGenderuwo = view.findViewById(R.id.etJmlGenderuwo);
        etJmlPocong = view.findViewById(R.id.etJmlPocong);
        etJmlKopi = view.findViewById(R.id.etJmlKopi);
        tvSubTotal = view.findViewById(R.id.tvSubTotal);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        tvDiskon = view.findViewById(R.id.tvJmlDiskon);



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etJmlNasgor.getText().toString().isEmpty()) {
                    nasgor = Integer.valueOf(etJmlNasgor.getText().toString());
                    bayar += nasgor * 10000; }
                if (!etJmlMie.getText().toString().isEmpty()) {
                    mie = Integer.valueOf(etJmlMie.getText().toString());
                    bayar += mie * 10000;}
                if (!etJmlSoto.getText().toString().isEmpty()) {
                    soto = Integer.valueOf(etJmlSoto.getText().toString());
                    bayar += soto * 15000;}
                if (!etJmlGenderuwo.getText().toString().isEmpty()) {
                    genderuwo = Integer.valueOf(etJmlGenderuwo.getText().toString());
                    bayar += genderuwo * 8000;}
                if (!etJmlPocong.getText().toString().isEmpty()) {
                    pocong = Integer.valueOf(etJmlPocong.getText().toString());
                    bayar += pocong * 6000;}
                if (!etJmlKopi.getText().toString().isEmpty()) {
                    kopi = Integer.valueOf(etJmlKopi.getText().toString());
                    bayar += nasgor * 5000;}
//
//                if(rbMember.isChecked()){
//                    diskon = bayar * 10 / 100; }
                 if (rbNonMember.isChecked()){
                    diskon = 10;
                }

                tvDiskon.setText("Rp." + diskon);
//
               tvSubTotal.setText("Rp." + bayar);

            }
        });
        return view;


    }


}

