package com.example.sara.navigation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ItemAdapterAvvisi extends BaseAdapter {

    LayoutInflater mInflater;
    ArrayList<String> mess1= new ArrayList<String>();
    ArrayList<String> data1= new ArrayList<String>();
    ArrayList<String> ora1= new ArrayList<String>();

    public static final String PREFS_NAME="MyPrefsFile";
    public static final String PREFS_USERNAME="username";
    public static final String PREFS_PASSWORD="password";

    String loginID;
    String loginPWD;


    String messaggio, dataa, hora;

    Intent intent;
    TextView messText, dataText, oraText;
    Context context;


    FloatingActionButton button , deleteButton;

    int posizioneBottone, posizioneDlt;



    public ItemAdapterAvvisi(Context c, ArrayList<String> m, ArrayList<String> d, ArrayList<String> o) {
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

        View v = mInflater.inflate(R.layout.mio_avvisi_detail, null);
        messText = (TextView) v.findViewById(R.id.mexText);
        dataText = (TextView) v.findViewById(R.id.dataText);
        oraText = (TextView) v.findViewById(R.id.oraText);


        button = (FloatingActionButton) v.findViewById(R.id.editBtn);
        deleteButton = (FloatingActionButton) v.findViewById(R.id.deleteBtn);

        SharedPreferences prefs = parent.getContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        loginID = prefs.getString(PREFS_USERNAME,null);

        loginPWD = prefs.getString(PREFS_PASSWORD,null);

        if(!(loginID.equals("sindaco"))){
            button.setVisibility(View.INVISIBLE);
            deleteButton.setVisibility(View.INVISIBLE);
        }



        //Log.d("count" , "Il numero è " + getCount());
        messaggio = mess1.get(position);
        dataa= data1.get(position);
        hora = ora1.get(position);


        button.setTag(position);
        deleteButton.setTag(position);

        messText.setText(messaggio);
        dataText.setText(dataa);
        oraText.setText(hora);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        posizioneBottone = Integer.parseInt(v.getTag().toString());

                        intent = new Intent( context, EditActivityAvvisi.class);

                       // Log.d("LOG" , "Il Tag cliccato è " + posizioneBottone );
                        Log.d("PosizioneBottone" , messaggio+"---> pos ->"+posizioneBottone);

                        Bundle mBundle = new Bundle();
                        mBundle.putString("key",mess1.get(posizioneBottone));


                        //mBundle.putInt("pos", pp);

                        intent.putExtras(mBundle);

                        //Toast.makeText((Activity)context, Integer.toString(o),Toast.LENGTH_SHORT).show();
                       ((Activity) context).startActivityForResult(intent, 1);
                        //Intent in = ((Activity)v.getContext()).getIntent();
                        //String value = in.getStringExtra("keyy");
                        //mess[position] = value;


                   // }
               // });

            }
        });
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        posizioneDlt = Integer.parseInt(v.getTag().toString());
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);

                        alert.setTitle("Confermi eliminazione?");
                        alert.setPositiveButton("SI", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                mess1.remove(posizioneDlt);
                                ora1.remove(posizioneDlt);
                                data1.remove(posizioneDlt);
                                notifyDataSetChanged();
                                Toast.makeText(context ,"Avviso eliminato con successo", Toast.LENGTH_SHORT).show();

                            }
                        });
                        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog dialog = alert.create();
                        dialog.show();
                    }
                });


        return v;
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();
                String result=b.getString("key2");
               // pos2 = b.getInt("pos2");
                //Log.d("PosizioneBottone2222" , result+"---> pos ->"+posizioneBottone);
                mess1.remove(posizioneBottone);
                mess1.add(posizioneBottone,result);
                notifyDataSetChanged();

                //String i = Integer.toString(pos2);

                Toast.makeText(context ,"Avviso modificato con successo", Toast.LENGTH_SHORT).show();
            }

        }
    }



}

