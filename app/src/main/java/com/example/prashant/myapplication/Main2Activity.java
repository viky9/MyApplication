package com.example.prashant.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    private static Toolbar toolbar;
    private static ViewPager viewPager;
    private static TabLayout tabLayout;
//    private static final String url = "http://13.126.70.14/test.php";
//
//    String titleya="poko     prashant    ";
//    String product[]=new String[100];
//    String offer[]=new String[100];
//    String p_product[]=new String[100];
//    String p_offer[]=new String[100];
//    String product_list="";
//    String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
//        name=preferences.getString("id","");
//        if(!name.equalsIgnoreCase(""))
//        {
//            name=name+"";  /* Edit the value here*/
//        }
//        show();




        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);//setting tab over viewpager

        //Implementing tab selected listener over tablayout
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());//setting current selected item over viewpager
                switch (tab.getPosition()) {
                    case 0:
                        Log.e("TAG","TAB1");
                        break;
                    case 1:
                        Log.e("TAG","TAB2");
                        break;
//                    case 2:
//                        Log.e("TAG","TAB3");
//                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    //Setting View Pager
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new tab1Activity("pokojjwr"), "MyOffers");
        adapter.addFrag(new tab2Activity("iOS"), "Other Offers");
//        adapter.addFrag(new DummyFragment("WINDOWS"), "WINDOWS");
        viewPager.setAdapter(adapter);
    }


    //View Pager fragments setting adapter class
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();//fragment arraylist
        private final List<String> mFragmentTitleList = new ArrayList<>();//title arraylist

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }


        //adding fragments and title method
        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

//    public void show() {
////
////        RequestQueue mRequestQueue;
////
////// Instantiate the cache
////        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
////
////// Set up the network to use HttpURLConnection as the HTTP client.
////        Network network = new BasicNetwork(new HurlStack());
////
////// Instantiate the RequestQueue with the cache and network.
////        mRequestQueue = new RequestQueue(cache, network);
////
////// Start the queue
////        mRequestQueue.start();
//
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e("TAG", response);
//                showJson(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Main2Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("email", name);
//                return params;
//            }
//        };
//
//       RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
//
//
//    public void showJson(String response) {
//        Log.d("TAG", response);
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            JSONArray jsonArray = jsonObject.getJSONArray("myresult");
//            int n = jsonArray.length();
//            for (int i = 0; i < n; i++) {
//                JSONObject movieObject = jsonArray.getJSONObject(i);
////                titleya=titleya+ movieObject.getString("product");
//                p_product[i]=movieObject.getString("product");
//                p_offer[i] = movieObject.getString("discount");
//            }
//            JSONArray jsonArray1 = jsonObject.getJSONArray("result");
//            int n1 = jsonArray1.length();
//            for (int i = 0; i < n1; i++) {
//                JSONObject movieObject = jsonArray1.getJSONObject(i);
//                titleya=titleya+ movieObject.getString("product");
//                product[i]=movieObject.getString("product");
//                offer[i] = movieObject.getString("discount");
//            }
////            textView1.setText(title);
////            textView1.setText(product[1]+"        po     "+offer[1]);
//        } catch (JSONException e) {
//            Toast.makeText(getApplicationContext(), "Not available", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//    }
//    public String[] getProducts(){
//        return product;
//
//    }
//    public String[] getOffers(){
//        return offer;
//
//    }
//    public String[] getP_Products(){
//        return p_product;
//
//    }
//    public String[] getP_Offers(){
//        return p_offer;
//
//    }
//    public String getpoo(){
//        return titleya+"hello";
//    }


}
