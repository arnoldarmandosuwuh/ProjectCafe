package com.cafe_papsi.aplikasipapsicafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import com.cafe_papsi.aplikasipapsicafe.utils.SharedPrefManager;

public class LoginActivity extends AppCompatActivity {
    private RequestQueue queue;
    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;
    private String username, password;
    SharedPrefManager sharedPrefManager;

    private boolean isValidInput() {

        if (username.length() < 8) {
            etUsername.setError("Username minimal 8 karakter");
            etUsername.requestFocus();
            return false;
        }
        if (username.isEmpty()) {
            etUsername.setError("Username tidak boleh kosong");
            etUsername.requestFocus();
            return false;
        }

        if (password.length() < 8) {
            etPassword.setError("Password minimal 8 karakter");
            etPassword.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password tidak boleh kosong");
            etPassword.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        queue = Volley.newRequestQueue(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.bLogin);
        btnRegister = findViewById(R.id.bRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                String url = "https://projectcafepapsi.000webhostapp.com/login.php";
                if (isValidInput()) {
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                int status = jsonResponse.getInt("status");
                                String message = jsonResponse.getString("message");
                                int id = jsonResponse.getInt("data");
                                String nama = jsonResponse.getString("nama_user");
//                                String gambar = jsonResponse.getString("gambar_user");

                                if (status == 0) {
                                    etUsername.setText("");
                                    etPassword.setText("");
                                }

                                sharedPrefManager = new SharedPrefManager(LoginActivity.this);

                                sharedPrefManager.saveSPString(SharedPrefManager.SP_ID, String.valueOf(id));
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
//                                sharedPrefManager.saveSPString(SharedPrefManager.SP_GAMBAR, gambar);
                                // Shared Pref ini berfungsi untuk menjadi trigger session login
                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();

//                            Toast.makeText(LoginActivity.this, status + " : " + message, Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("username", username);
                            params.put("password", password);

                            return params;
                        }
                    };

                    queue.add(request);
                }
            }
        });

    }
}
