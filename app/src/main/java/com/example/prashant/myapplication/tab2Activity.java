package com.example.prashant.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by prashant on 29-08-2017.
 */


public class tab2Activity extends Fragment {
    private View view;
    int len=0;
    String name="";
    String productList[]=new String[100];
    String offerList[]=new String[100];

    private String title;//String for tab title

    private static RecyclerView recyclerView;

    public tab2Activity() {
    }

    public tab2Activity(String title) {
        this.title = title;//Setting tab title
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab2, container, false);
        setRetainInstance(true);

//        Main2Activity activity = (Main2Activity) getActivity();
//        product = activity.getProducts();
//        offer=activity.getOffers();
//
//        if(l2==0){
//            for(int i=0;product[i]!=null;i++)
//                l2++;
//        }

//        if(count==0) {
//            SharedPreferences.Editor editor = getActivity().getSharedPreferences("hiii", Context.MODE_PRIVATE).edit();
////            Editor edit = prefs.edit();
//            StringBuilder sb = new StringBuilder();
//            StringBuilder sb1 = new StringBuilder();
//            for (int i = 0; i < product.length; i++) {
//                sb.append(product[i]).append(",");
//                sb1.append(offer[i]).append(",");
//            }
//            count=10;
//            editor.putString("ProductList", sb.toString());
//            editor.putString("OfferList",sb1.toString());
//            editor.commit();
//           // count=10;

//        }

        SharedPreferences prefs = getActivity().getSharedPreferences("hiii1", Context.MODE_PRIVATE);
        name+= prefs.getString("ProductList", null);
        String l=prefs.getString("length",null);
        Log.e("length  tab2   : ",l);
        len=Integer.valueOf(l);
        productList=name.split(",");
        Log.e("tab2"," ProductList   :   "+name);
        name= prefs.getString("OfferList", null);
        offerList=name.split(",");
        Log.e("tab2"," offer List   :   "+name);


        setRecyclerView();
        return view;

    }

    public int getlengtho(){
        int length=0;
        for (int i=0;productList[i]!="null"||i<50;i++){
                length++;

        }

        return  length;
    }


    //Setting recycler view
    private void setRecyclerView() {

        recyclerView = (RecyclerView) view
                .findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView
                .setLayoutManager(new LinearLayoutManager(getActivity()));//Linear Items


        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 1; i<len; i++) {
            arrayList.add("tpnb:   "+productList[i]+"   Discount: " + offerList[i]+"%");//Adding items to recycler view
        }
        RecyclerView_Adapter adapter = new RecyclerView_Adapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview

    }


}
