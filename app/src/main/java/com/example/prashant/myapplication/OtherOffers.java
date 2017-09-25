package com.example.prashant.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OtherOffers extends AppCompatActivity {

    String name;
    String title="";
    String product[]=new String[100];
    String offer[]=new String[100];

    private static final String url = "http://192.168.42.166/FcmExample/test3.php";
    //ProgressDialog progressDialog;
    TextView textView1;
    TextView textView2;
    // String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_offers);
        textView1 = (TextView) findViewById(R.id.textView);
//        textView2=(TextView)findViewById(R.id.textView9);
        show();
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(this);
        name=preferences.getString("id","");
        if(!name.equalsIgnoreCase(""))
        {
            name=name+"  ";  /* Edit the value here*/
        }

//        textView1.setText(name);

    }

    public void show() {
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                showJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OtherOffers.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void showJson(String response) {
        Log.d("TAG", response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            int n = jsonArray.length();
            for (int i = 0; i < n; i++) {
                JSONObject movieObject = jsonArray.getJSONObject(i);
                title=title+ movieObject.getString("product");
                product[i]=movieObject.getString("product");
                offer[i] = movieObject.getString("discount");
            }
//            textView1.setText(title);
            textView1.setText(product[1]+"        po     "+offer[1]);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Not available", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}

