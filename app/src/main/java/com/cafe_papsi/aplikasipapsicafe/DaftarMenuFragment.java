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
import com.cafe_papsi.aplikasipapsicafe.adapter.AdapterMenu;
import com.cafe_papsi.aplikasipapsicafe.model.Menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarMenuFragment extends Fragment {

    private RequestQueue requestQueue;
    private ArrayList<Menu> listMenu;
    private RecyclerView rvMenu;
    private AdapterMenu adapterMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daftar_menu, container, false);

        rvMenu = view.findViewById(R.id.rvMenu);
        rvMenu.setAdapter(adapterMenu);
        rvMenu.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Daftar Menu");

        requestQueue = Volley.newRequestQueue(getContext());
        listMenu = new ArrayList<>();

        adapterMenu = new AdapterMenu(getContext(), listMenu);
    }

    @Override
    public void onResume() {
        super.onResume();

        String url = "http://192.168.8.101:8080/ProjectCafe/ambil_menu.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray  = new JSONArray(jsonObject.getString("data"));

                    listMenu.clear();
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonItem = jsonArray.getJSONObject(i);
                        String id = jsonItem.getString("id");
                        String menu = jsonItem.getString("menu");
                        String harga = jsonItem.getString("harga");
                        String gambar = jsonItem.getString("gambar");

                        listMenu.add(new Menu(id, menu, harga, gambar));
                    }
                    adapterMenu.notifyDataSetChanged();
                } catch (JSONException e) {
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
