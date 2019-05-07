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
import android.widget.TextView;

import com.cafe_papsi.aplikasipapsicafe.face.Listener;


public class PembayaranFragment extends Fragment implements Listener {

private Button btnSubmit;
private TextView tvDiskon, tvSubTotal, tvHargaNasgor, tvHargaMie, tvHargaSoto, tvHargaGenderuwo, tvHargaPocong, tvHargaKopi, tvBayar;
private RadioButton rbMember, rbNonMember;
private EditText etMeja,etJmlNasgor,etJmlMie,etJmlSoto,etJmlGenderuwo,etJmlPocong,etJmlKopi;
private int nasgor,mie,soto,genderuwo,pocong,kopi, bayar;

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
        etJmlMie =  view.findViewById(R.id.etJmlMie);
        etJmlSoto = view.findViewById(R.id.etJmlSoto);
        etJmlGenderuwo = view.findViewById(R.id.etJmlGenderuwo);
        etJmlPocong = view.findViewById(R.id.etJmlPocong);
        etJmlKopi = view.findViewById(R.id.etJmlKopi);

//        nasgor = etJmlNasgor * 10000;
//        mie = etJmlMie * 10000;
//        soto = etJmlSoto * 15000;
//        genderuwo = etJmlGenderuwo * 8000;
//        pocong = etJmlPocong * 6000;
//        kopi = etJmlKopi * 5000;

        bayar = nasgor + mie + soto + genderuwo + pocong + kopi;
        tvBayar = view.findViewById(R.id.tvBayar);
        return view;
    }


    @Override
    public void onSucessWs(Object[] objects) {

        }

    @Override
    public void onFailedWs(Object[] objects) {

    }
}

