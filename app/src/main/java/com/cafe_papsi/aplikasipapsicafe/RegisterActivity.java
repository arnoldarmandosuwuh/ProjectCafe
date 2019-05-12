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
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private RequestQueue queue;
    private EditText etName, etUsername, etPassword, etConfirmPassword;
    private Button bRegister;
    private String name, username, password, confirmPassword;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private boolean isValidInput() {

        if (name.length() < 6) {
            etName.setError("Nama minimal 6 karakter");
            etName.requestFocus();
            return false;
        }
        if (name.isEmpty()) {
            etName.setError("Nama tidak boleh kosong");
            etName.requestFocus();
            return false;
        }

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

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Password tidak sama");
            etConfirmPassword.requestFocus();
            return false;
        }
        if (confirmPassword.isEmpty()) {
            etPassword.setError("Confirm Password tidak boleh kosong");
            etPassword.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        queue = Volley.newRequestQueue(this);

        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirm);
        bRegister = findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = etName.getText().toString();
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                confirmPassword = etConfirmPassword.getText().toString();
                String url = "http://192.168.8.100:8080/ProjectCafe/registrasi.php";
                if (isValidInput()) {
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                int status = jsonResponse.getInt("status");
                                String message = jsonResponse.getString("message");

                                if (status == 0) {
                                    etName.setText("");
                                    etUsername.setText("");
                                    etPassword.setText("");
                                    etConfirmPassword.setText("");
                                }
                                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                                onBackPressed();
                            } catch (JSONException e) {
                                Toast.makeText(RegisterActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("name", name);
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