package com.example.sara.navigation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sara on 23/06/2018.
 */
public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    ArrayList<String> mess1= new ArrayList<String>();



    String messaggio;

    TextView messText, qText;
    Context context;

    int pos2=0;

    Button button;


    public ItemAdapter(Context c, ArrayList<String> m) {
        mess1=m;
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

        View v = mInflater.inflate(R.layout.my_risorse_detail, null);
        messText = (TextView) v.findViewById(R.id.mexText);


         button = (Button) v.findViewById(R.id.confermaBtn);


        Log.d("count" , "Il numero è " + getCount());
        messaggio = mess1.get(position);



        messText.setText(messaggio);



        return v;
    }



    //collegato con activityresult di main activity

  /*  public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();
                String result=b.getString("key2");
                String result1=b.getString("key1");
                String result2=b.getString("key3");
                String result3=b.getString("key4");





                notifyDataSetChanged();

            }

        }
    }*/



}


