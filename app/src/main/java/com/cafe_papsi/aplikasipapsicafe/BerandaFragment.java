package com.cafe_papsi.aplikasipapsicafe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cafe_papsi.aplikasipapsicafe.utils.SharedPrefManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {

    TextView tvUser;
    String idUser;
    SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sharedPrefManager = new SharedPrefManager(getContext());

        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        tvUser = view.findViewById(R.id.tvUser);
        idUser = sharedPrefManager.getSPNama();

        tvUser.setText(idUser);

        return view;

    }

}
