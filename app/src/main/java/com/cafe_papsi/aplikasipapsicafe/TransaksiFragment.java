package com.cafe_papsi.aplikasipapsicafe;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cafe_papsi.aplikasipapsicafe.adapter.AdapterTransaksi;
import com.cafe_papsi.aplikasipapsicafe.model.Transaksi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransaksiFragment extends Fragment {

    private RequestQueue requestQueue;
    private ArrayList<Transaksi> listTransaksi;
    private RecyclerView rvTransaksi;
    private AdapterTransaksi adapterTransaksi;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaksi, container, false);

        rvTransaksi = view.findViewById(R.id.rvTransaksi);
        rvTransaksi.setAdapter(adapterTransaksi);
        rvTransaksi.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Transaksi");

        requestQueue = Volley.newRequestQueue(getContext());
        listTransaksi = new ArrayList<>();

        adapterTransaksi = new AdapterTransaksi(getContext(), listTransaksi);
    }

    @Override
    public void onResume() {
        super.onResume();

        String url = "https://projectcafepapsi.000webhostapp.com/ambil_transaksi.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));

                    listTransaksi.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonItem = jsonArray.getJSONObject(i);
                        String id = jsonItem.getString("id");
                        String idUser = jsonItem.getString("idUser");
                        String noMeja = jsonItem.getString("noMeja");
                        String total = jsonItem.getString("total");
                        String diskon = jsonItem.getString("diskon");
                        String tglTransaksi = jsonItem.getString("tglTransaksi");

                        listTransaksi.add(new Transaksi(id, idUser, noMeja, total, diskon, tglTransaksi));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }
}
