package com.example.prashant.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class homeActivity extends AppCompatActivity {


    private static final String url = "http://13.126.70.14/test2.php";

    String name;

    // Button Logout
    Button btnLogout;
    TextView myTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        TextView myTextView = (TextView) findViewById(R.id.tv_o);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue.otf");
        myTextView.setTypeface(typeface);
        TextView myTextView1 = (TextView) findViewById(R.id.textView2);

        myTextView1.setTypeface(typeface);
        TextView myTextView2 = (TextView) findViewById(R.id.textView3);

        myTextView2.setTypeface(typeface);
        myTextView3 = (TextView) findViewById(R.id.textView4);

        myTextView3.setTypeface(typeface);

        //String name;


        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        name=preferences.getString("id","");
        if(!name.equalsIgnoreCase(""))
        {
            name=name+"  ";  /* Edit the value here*/
        }

        show();


    }


    public void startoffer(View v){
        Intent intent=new Intent(getBaseContext(),myoffer.class);
        startActivity(intent);
    }

    public void alloffer(View v){
        Intent intent=new Intent(getBaseContext(),Main2Activity.class);
        startActivity(intent);
    }
    public void testoffer(View v){
        Intent intent=new Intent(getBaseContext(),OtherOffers.class);
        startActivity(intent);
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

                           // myTextView3.setText(response);


                            Log.e("succcess", "success");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("error except", "errror except");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        textView.setText("error\n");
                        Log.e("error", "errror");
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
    }
}
