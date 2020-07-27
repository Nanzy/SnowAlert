package com.example.sara.navigation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapterRegistro extends BaseAdapter {

    LayoutInflater mInflater;
    ArrayList<String> mess1= new ArrayList<String>();
    ArrayList<String> data1= new ArrayList<String>();
    ArrayList<String> ora1= new ArrayList<String>();


    String messaggio, dataa, hora;

    Intent intent;
    TextView messText, dataText, oraText;
    Context context;


    public ItemAdapterRegistro(Context c, ArrayList<String> m, ArrayList<String> d, ArrayList<String> o) {
        mess1=m;
        data1=d;
        ora1=o;
        context = c;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mess1.size();
    }

    @Override
    public Object getItem(int position) {
        return mess1.get(position);
    }

    public void setItem(int position, String value) {
        mess1.add(position,value);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.my_registro_detail, null);
        messText = (TextView) v.findViewById(R.id.mexText);
        dataText = (TextView) v.findViewById(R.id.dataText);
        oraText = (TextView) v.findViewById(R.id.oraText);

        messaggio = mess1.get(position);
        dataa= data1.get(position);
        hora = ora1.get(position);

        messText.setText(messaggio);
        dataText.setText(dataa);
        oraText.setText(hora);



        return v;
    }





}
