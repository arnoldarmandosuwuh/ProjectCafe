package com.cafe_papsi.aplikasipapsicafe;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {
    private  String menu, harga, deskripsi, gambar;
    private RequestQueue requestQueue;
    private EditText etUpMenu, etUpHarga, etUpDeskripsi, etGambar;
    private ImageView ivUpGambar;
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_menu);
        setTitle("Insert Menu");

        etUpMenu = findViewById(R.id.etUpMenu);
        etUpHarga = findViewById(R.id.etUpHarga);
        etUpDeskripsi = findViewById(R.id.etUpDeskripsi);
        etGambar = findViewById(R.id.etGambar);
        ivUpGambar = findViewById(R.id.ivUpGambar);
        btnSubmit =  findViewById(R.id.btnSubmit);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu = etUpMenu.getText().toString();
                harga = etUpHarga.getText().toString();
                deskripsi = etUpDeskripsi.getText().toString();
                gambar = etGambar.getText().toString();

                String url = "https://projectcafepapsi.000webhostapp.com/insert_menu.php";
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int status = jsonObject.getInt("status");
                            String message = jsonObject.getString("message");

                            if (status == 0){
                                etUpMenu.setText("");
                                etUpHarga.setText("");
                                etUpDeskripsi.setText("");
                                etGambar.setText("");
                            }
                            Toast.makeText(MenuActivity.this, message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } catch (JSONException e) {
                            Toast.makeText(MenuActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MenuActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
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
