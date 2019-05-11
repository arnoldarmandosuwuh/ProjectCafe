package com.cafe_papsi.aplikasipapsicafe;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cafe_papsi.aplikasipapsicafe.utils.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;


public class PembayaranFragment extends Fragment {

    private Button btnSubmit, btnBayar;
    private TextView tvDiskon, tvSubTotal, tvSubTotalBayar;
    RadioGroup radioGroup;
    private EditText etMeja, etJmlNasgor, etJmlMie, etJmlSoto, etJmlGenderuwo, etJmlPocong, etJmlKopi, etBayar;
    private double nasgor, mie, soto, genderuwo, pocong, kopi, diskon = 0, total = 0, subTotal = 0;
    private String noMeja, idUser;
    private int kembalian, bayar;
    private RequestQueue queue;
    SharedPrefManager sharedPrefManager;

    NumberFormat formatter = new DecimalFormat("#,###");

    private boolean isValidInput() {
        if (noMeja.isEmpty()) {
            etMeja.setError("Nomor meja tidak boleh kosong");
            etMeja.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pembayaran, container, false);

        sharedPrefManager = new SharedPrefManager(getContext());
        idUser = sharedPrefManager.getSPId();

        queue = Volley.newRequestQueue(getContext());

        etMeja = view.findViewById(R.id.etMeja);
        etJmlNasgor = view.findViewById(R.id.etJmlNasgor);
        etJmlMie = view.findViewById(R.id.etJmlMie);
        etJmlSoto = view.findViewById(R.id.etJmlSoto);
        etJmlGenderuwo = view.findViewById(R.id.etJmlGenderuwo);
        etJmlPocong = view.findViewById(R.id.etJmlPocong);
        etJmlKopi = view.findViewById(R.id.etJmlKopi);
        etBayar = view.findViewById(R.id.etBayar);
        radioGroup = view.findViewById(R.id.rgBayar);
        tvSubTotal = view.findViewById(R.id.tvSubTotal);
        tvDiskon = view.findViewById(R.id.tvJmlDiskon);
        tvSubTotalBayar = view.findViewById(R.id.tvSubTotalBayar);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnBayar = view.findViewById(R.id.btnBayar);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etJmlNasgor.getText().toString().isEmpty()) {
                    nasgor = Integer.valueOf(etJmlNasgor.getText().toString());
                    total += nasgor * 10000;
                }
                if (!etJmlMie.getText().toString().isEmpty()) {
                    mie = Integer.valueOf(etJmlMie.getText().toString());
                    total += mie * 10000;
                }
                if (!etJmlSoto.getText().toString().isEmpty()) {
                    soto = Integer.valueOf(etJmlSoto.getText().toString());
                    bayar += soto * 15000;
                }
                if (!etJmlGenderuwo.getText().toString().isEmpty()) {
                    genderuwo = Integer.valueOf(etJmlGenderuwo.getText().toString());
                    total += genderuwo * 8000;
                }
                if (!etJmlPocong.getText().toString().isEmpty()) {
                    pocong = Integer.valueOf(etJmlPocong.getText().toString());
                    total += pocong * 6000;
                }
                if (!etJmlKopi.getText().toString().isEmpty()) {
                    kopi = Integer.valueOf(etJmlKopi.getText().toString());
                    total += nasgor * 5000;
                }

                int statusTerpilih = radioGroup.getCheckedRadioButtonId();

                if (statusTerpilih == R.id.rbMember) {
                    diskon += total * 0.1;
                } else if (statusTerpilih == R.id.rbNonMember) {
                    diskon = 0;
                }

                subTotal = total - diskon;
                tvDiskon.setText("Rp. " + formatter.format(diskon));
                tvSubTotal.setText("Rp. " + formatter.format(total));
                tvSubTotalBayar.setText("Rp. " + formatter.format(subTotal));

            }
        });

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noMeja = etMeja.getText().toString();
                bayar = Integer.valueOf(etBayar.getText().toString());
                kembalian = bayar - (int) Math.round(subTotal);


                String url = "http://192.168.8.101:8080/ProjectCafe/transaksi.php";
                if (isValidInput()) {
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                int status = jsonResponse.getInt("status");
                                String message = jsonResponse.getString("message");
                                if (status == 0) {
                                    etMeja.setText("");
                                    etJmlNasgor.setText("");
                                    etJmlMie.setText("");
                                    etJmlSoto.setText("");
                                    etJmlGenderuwo.setText("");
                                    etJmlPocong.setText("");
                                    etJmlKopi.setText("");
                                    etBayar.setText("");
                                    tvDiskon.setText("Rp. ");
                                    tvSubTotal.setText("Rp. ");
                                    tvSubTotalBayar.setText("Rp. ");
                                }
                                Toast.makeText(getContext(),  message, Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("idUser", idUser);
                            params.put("noMeja", noMeja);
                            params.put("total", String.valueOf(Math.round(subTotal)));
                            params.put("diskon", String.valueOf(Math.round(diskon)));

                            return params;
                        }
                    };
                    queue.add(request);
                }
                String pesan = "Order \n";
                pesan += "Kasir ID : " + idUser + "\n";
                pesan += "Nomor Meja : " + noMeja + "\n";
                pesan += "Kembalian = " + kembalian;

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setMessage(pesan);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
        return view;


    }


}

