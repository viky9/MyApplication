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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class myoffer extends AppCompatActivity {

    String name;

    private static final String url = "http://192.168.42.166/FcmExample/myoffer.php";
    //ProgressDialog progressDialog;
    TextView textView1;
    TextView textView2;
   // String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myoffer);
        textView1 = (TextView) findViewById(R.id.textView8);
        textView2=(TextView)findViewById(R.id.textView9);
        show();
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(this);
        name=preferences.getString("id","");
        if(!name.equalsIgnoreCase(""))
        {
            name=name+"  ";  /* Edit the value here*/
        }

        textView1.setText(name);

    }

    public void show() {
        RequestQueue mRequestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

// Start the queue
        mRequestQueue.start();

// Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                          //  textView1.setText("Hello ClubCard User ID   "+name+ " your personalized offers are :   \n\n\n ");
                            textView1.setText(response);

                            Log.e("succcess","success");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("error except","errror except");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        textView.setText("error\n");
                        Log.e("error","errror");
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", name);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
// Add the request to the RequestQueue.
       // MySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
}
