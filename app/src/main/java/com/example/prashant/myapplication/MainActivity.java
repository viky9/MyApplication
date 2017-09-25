package com.example.prashant.myapplication;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //defining views
    private Button buttonRegister;
    private EditText editTextEmail;
    private ProgressDialog progressDialog;
    boolean flag=false;

    //URL to RegisterDevice.php
    private static final String URL_REGISTER_DEVICE = "http://13.126.70.14/RegisterDevice.php";
//    TextView textView=(TextView)  findViewById(R.id.textView);

    String titleya="poko     prashant    ";
    String product[]=new String[100];
    String offer[]=new String[100];
    String p_product[]=new String[100];
    String p_offer[]=new String[100];
    String product_list="";
    String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting views from xml
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);


        //adding listener to view
        buttonRegister.setOnClickListener(this);
    }

    //storing token to mysql server
    //storing token to mysql server
    private void sendTokenToServer() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing...");
        progressDialog.show();

        //final String token = SharedPrefManager.getInstance(this).getDeviceToken();
        final String token= FirebaseInstanceId.getInstance().getToken();
        final String email = editTextEmail.getText().toString();
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//Give any name for //preference as I have given "IDvalue" and value 0.
        SharedPreferences.Editor editor = mPrefs.edit();
        Log.e("Main Activity", "Refreshed token: " + token);
        editor.putString("id", email);
// give key value as "sound" you mentioned and value what you // to want give as "1" in you mentioned
        editor.commit();

        if (token == null) {
            progressDialog.dismiss();
            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(MainActivity.this, obj.getString("message"), Toast.LENGTH_LONG).show();
//                            textView.setText("registered\n");
                            Log.e("succcess",response);
                            flag=true;
                            showJson(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("error except","errror except");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
//                        textView.setText("error\n");
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("error Response","errror Response ");
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("token", token);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void showJson(String response) {
        Log.d("TAG", response);
        try {
            SharedPreferences.Editor editor1 = getSharedPreferences("hiii", Context.MODE_PRIVATE).edit();
            StringBuilder sb3 = new StringBuilder();
            StringBuilder sb4 = new StringBuilder();
            StringBuilder sb5=new StringBuilder();
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("myresult");
            int n = jsonArray.length();
            sb5.append(n);
            editor1.putString("length",sb5.toString());
            Log.e("my array length  ",sb5.toString());
            for (int i = 0; i < n; i++) {
                JSONObject movieObject = jsonArray.getJSONObject(i);
//                titleya=titleya+ movieObject.getString("product");
                p_product[i]=movieObject.getString("product");
                p_offer[i] = movieObject.getString("discount");
//                SharedPreferences.Editor editor = getSharedPreferences("hiii1", Context.MODE_PRIVATE).edit();
////            Editor edit = prefs.edit();
//                StringBuilder sb = new StringBuilder();
//                StringBuilder sb1 = new StringBuilder();
                for (int k = 0; k < product.length; k++) {
                    sb3.append(p_product[k]).append(",");
                    sb4.append(p_offer[k]).append(",");
                }
               // count1=10;
                Log.e("sb3",sb3.toString());
                Log.e("sb4",sb4.toString());
//
//
                editor1.putString("ProductList", sb3.toString());
                editor1.putString("OfferList",sb4.toString());

                editor1.commit();

            }
            SharedPreferences.Editor editor = getSharedPreferences("hiii1", Context.MODE_PRIVATE).edit();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2=new StringBuilder();
            JSONArray jsonArray1 = jsonObject.getJSONArray("result");
            int n1 = jsonArray1.length();
            sb2.append(n1);
            editor.putString("length",sb2.toString());
            for (int i = 0; i < n1; i++) {
                JSONObject movieObject = jsonArray1.getJSONObject(i);
                titleya=titleya+ movieObject.getString("product");
                product[i]=movieObject.getString("product");
                offer[i] = movieObject.getString("discount");

//            Editor edit = prefs.edit();

                for (int k = 0; k < product.length; k++) {
                    sb.append(product[k]).append(",");
                    sb1.append(offer[k]).append(",");
                }
                // count1=10;

            }

            Log.e("sb",sb.toString());
            Log.e("sb1",sb1.toString());


            editor.putString("ProductList", sb.toString());
            editor.putString("OfferList",sb1.toString());

            editor.commit();

            Log.e("Json responde",titleya);
//            textView1.setText(title);
//            textView1.setText(product[1]+"        po     "+offer[1]);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Not available", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister) {
            sendTokenToServer();
            if(flag){
                Intent i=new Intent(getBaseContext(),Main2Activity.class);
                startActivity(i);
            }
        }
    }
}

