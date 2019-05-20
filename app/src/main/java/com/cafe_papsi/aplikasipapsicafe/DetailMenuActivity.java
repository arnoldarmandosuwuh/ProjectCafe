package com.cafe_papsi.aplikasipapsicafe;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailMenuActivity extends AppCompatActivity {
    private String idMenu;
    private RequestQueue requestQueue;
    private ImageView ivMenu;
    private TextView tvNamaMenu, tvHargaMenu, tvDeskripsiMenu;
    private Button btnUpdate, btnDelete;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DetailMenuActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);

        ivMenu = findViewById(R.id.ivMenu);
        tvNamaMenu = findViewById(R.id.tvNamaMenu);
        tvHargaMenu = findViewById(R.id.tvHargaMenu);
        tvDeskripsiMenu = findViewById(R.id.tvDeskripsiMenu);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnSubmit);

        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();

            idMenu = bundle.getString("idMenu");
            requestQueue = Volley.newRequestQueue(getApplicationContext());

            String url = "https://projectcafepapsi.000webhostapp.com/ambil_menu_detail.php?id=" + idMenu;
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
                        JSONObject jsonResponse = jsonArray.getJSONObject(0);
                        String id = jsonResponse.getString("id");
                        String menu = jsonResponse.getString("menu");
                        String harga = jsonResponse.getString("harga");
                        String gambar = jsonResponse.getString("gambar");
                        String deskripsi = jsonResponse.getString("deskripsi");

                        setTitle(menu);
                        tvNamaMenu.setText(menu);
                        tvHargaMenu.setText(harga);
                        tvDeskripsiMenu.setText(deskripsi);

                        if (!gambar.trim().isEmpty()){
                            Picasso.get().load(gambar).into(ivMenu);
                        }else {
                            Picasso.get().load(R.drawable.no_image).into(ivMenu);
                        }

                    } catch (JSONException e) {
                        Toast.makeText(DetailMenuActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        ;
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(DetailMenuActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(request);
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://projectcafepapsi.000webhostapp.com/delete_menu.php?id=" + idMenu;
                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int status = jsonObject.getInt("status");
                            String message = jsonObject.getString("message");

                            if (status == 0){
                                Toast.makeText(DetailMenuActivity.this, message, Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(DetailMenuActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailMenuActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(request);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentUpdate = new Intent(DetailMenuActivity.this, UpdateActivity.class);
                intentUpdate.putExtra("idMenu",idMenu);
                startActivity(intentUpdate);
            }

        });

    }
}
