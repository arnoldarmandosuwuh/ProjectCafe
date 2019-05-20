package com.cafe_papsi.aplikasipapsicafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    private  String idMenu, menu, harga, deskripsi, gambar;
    private RequestQueue requestQueue;
    private EditText etUpMenu, etUpHarga, etUpDeskripsi, etGambar;
    private ImageView ivUpGambar;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_menu);

        etUpMenu = findViewById(R.id.etUpMenu);
        etUpHarga = findViewById(R.id.etUpHarga);
        etUpDeskripsi = findViewById(R.id.etUpDeskripsi);
        etGambar = findViewById(R.id.etGambar);
        ivUpGambar = findViewById(R.id.ivUpGambar);
        btnSubmit =  findViewById(R.id.btnSubmit);

        if (getIntent() != null){
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

                        setTitle("Update " + menu);
                        etUpMenu.setText(menu);
                        etUpHarga.setText(harga);
                        etUpDeskripsi.setText(deskripsi);
                        etGambar.setText(gambar);

                        if (!gambar.trim().isEmpty()){
                            Picasso.get().load(gambar).into(ivUpGambar);
                        }else {
                            Picasso.get().load(R.drawable.no_image).into(ivUpGambar);
                        }

                    } catch (JSONException e) {
                        Toast.makeText(UpdateActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(UpdateActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(request);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu = etUpMenu.getText().toString();
                harga = etUpHarga.getText().toString();
                deskripsi = etUpDeskripsi.getText().toString();
                gambar = etGambar.getText().toString();
                requestQueue = Volley.newRequestQueue(getApplicationContext());


                String url = "https://projectcafepapsi.000webhostapp.com/update_menu.php";
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int status = jsonResponse.getInt("status");
                            String message = jsonResponse.getString("message");

                            if (status == 0) {
                                etUpMenu.setText("");
                                etUpHarga.setText("");
                                etUpDeskripsi.setText("");
                                etGambar.setText("");
                            }
                            Toast.makeText(UpdateActivity.this, message, Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        } catch (JSONException e) {
                            Toast.makeText(UpdateActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("idMenu", idMenu);
                        params.put("menu", menu);
                        params.put("harga", harga);
                        params.put("gambar", gambar);
                        params.put("deskripsi", deskripsi);

                        return params;
                    }
                };
                requestQueue.add(request);
            }
        });


    }
}
