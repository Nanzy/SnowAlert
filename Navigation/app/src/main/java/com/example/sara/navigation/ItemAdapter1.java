package com.example.sara.navigation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sara on 23/06/2018.
 */
public class ItemAdapter1 extends BaseAdapter {

    LayoutInflater mInflater;

    ArrayList<String> quantita= new ArrayList<String>();


    String messaggio;

    TextView messText;
    Context context;




    public ItemAdapter1(Context c, ArrayList<String> q) {
        quantita=q;
        context = c;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return quantita.size();
    }

    @Override
    public Object getItem(int position) {
        return quantita.get(position);
    }

    public void setItem(int position, String value) {
        quantita.add(position,value);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.my_risorse_detail, null);
        messText = (TextView) v.findViewById(R.id.mexText);


        Log.d("count" , "Il numero è " + getCount());
        messaggio = quantita.get(position);




        messText.setText(messaggio);



        return v;
    }







}


