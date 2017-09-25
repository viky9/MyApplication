package com.example.prashant.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by prashant on 29-08-2017.
 */


public class tab1Activity extends Fragment {
    private static final String url = "http://192.168.42.166/FcmExample/test.php";
    private View view;
    int len=0;
    private String name="";
    String productList[]=new String[100];
    String offerList[]=new String[100];
    static int l1=0;
//    String combo="poko";

    private String title;//String for tab title

    private static RecyclerView recyclerView;

    public tab1Activity() {
    }

    public tab1Activity(String title) {
        this.title = title;//Setting tab title
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab1, container, false);
        setRetainInstance(true);
//        Main2Activity activity = (Main2Activity) getActivity();
//        product = activity.getP_Products();
//        offer=activity.getP_Offers();
////        combo+=activity.getpoo();
//
//        if(l1==0){
//            for(int i=0;product[i]!=null;i++)
//                l1++;
//        }

//        if(count1==0) {
//            SharedPreferences.Editor editor = getActivity().getSharedPreferences("hiii1", Context.MODE_PRIVATE).edit();
////            Editor edit = prefs.edit();
//            StringBuilder sb = new StringBuilder();
//            StringBuilder sb1 = new StringBuilder();
//            for (int i = 0; i < product.length; i++) {
//                sb.append(product[i]).append(",");
//                sb1.append(offer[i]).append(",");
//            }
//            count1=10;
//            editor.putString("ProductList", sb.toString());
//            editor.putString("OfferList",sb1.toString());
//            editor.commit();
//            // count=10;
//
//        }
//        SharedPreferences prefs1 = getActivity().getSharedPreferences("hiii", Context.MODE_PRIVATE);
//        String l1=prefs1.getString("length",null);
//        Log.e("length     my ",l1);

        SharedPreferences prefs = getActivity().getSharedPreferences("hiii", Context.MODE_PRIVATE);
        name+= prefs.getString("ProductList", null);
        productList=name.split(",");
        String l=prefs.getString("length",null);
        Log.e("length : ",l);
        len=Integer.valueOf(l);
        Log.e("tab1"," ProductList   :   "+productList[0]+"      "+productList[1]);
        name= prefs.getString("OfferList", null);
        offerList=name.split(",");
        Log.e("tab1"," offer List   :   "+offerList[0]+"    "+offerList[1]);


        setRecyclerView();
        return view;

    }

    //Setting recycler view
    private void setRecyclerView() {

        recyclerView = (RecyclerView) view
                .findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView
                .setLayoutManager(new LinearLayoutManager(getActivity()));//Linear Items


        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0;i<len; i++) {
            arrayList.add("tpnb:   "+productList[i]+"   Discount: " + offerList[i]+"%");//Adding items to recycler view
        }
        RecyclerView_Adapter adapter = new RecyclerView_Adapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview

    }


}