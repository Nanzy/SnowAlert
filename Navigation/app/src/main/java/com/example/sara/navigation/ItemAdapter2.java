package com.example.sara.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.sara.navigation.Meteo.PREFS_NAME;

/**
 * Created by Sara on 23/06/2018.
 */
public class ItemAdapter2 extends BaseAdapter {

    public static final String PREFS_NAME="MyPrefsFile";
    public static final String PREFS_USERNAME="username";
    public static final String PREFS_PASSWORD="password";

    String loginID;
    String loginPWD;

    LayoutInflater mInflater;
    ArrayList<String> mess1= new ArrayList<String>();


    FloatingActionButton check;

    String messaggio, dataa, ora;

    Intent intent;
    TextView messText, dataText, oraText;
    Context context;

    int pos2=0;

    FloatingActionButton button , deleteButton;

    int posizioneBottone, posizioneDlt;


    public ItemAdapter2(Context c, ArrayList<String> m) {
        mess1=m;

        context = c;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    /**public ItemAdapter2(Context c, Row r) {
     row = r;
     context = c;
     mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

     }**/

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
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View v = mInflater.inflate(R.layout.my_avvisi_detail, null);
        messText = (TextView) v.findViewById(R.id.mexText);

        check= (FloatingActionButton) v.findViewById(R.id.check);

        button = (FloatingActionButton) v.findViewById(R.id.editBtn);
        deleteButton = (FloatingActionButton) v.findViewById(R.id.deleteBtn);



        Log.d("count" , "Il numero è " + getCount());
        messaggio = mess1.get(position);


        //Log.d("LOG" , "Il messaggio è " + messaggio );

        button.setTag(position);
        deleteButton.setTag(position);

        messText.setText(messaggio);

        SharedPreferences prefs = parent.getContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loginID = prefs.getString(PREFS_USERNAME,null);

        loginPWD = prefs.getString(PREFS_PASSWORD,null);


        if(!(loginID.equals("sindaco"))){
            button.setVisibility(View.INVISIBLE);
            deleteButton.setVisibility(View.INVISIBLE);
        }

        if (loginID.equals("sindaco")){
            check.setVisibility(View.INVISIBLE);
        }

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                //  builder.setTitle("Confirm dialog demo !");
                builder.setMessage("Sei sicuro di voler completare questo intervento?");
                builder.setCancelable(false);
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //check.setBackgroundTintList(ColorStateList.valueOf(0xff99cc00));

                        parent.getChildAt(position).setBackgroundColor(0xff99cc00);
                        check.setVisibility(View.INVISIBLE);
                        //convertView.setBackgroundColor(Color.GREEN);

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(context, "You've changed your mind to delete all records", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();



            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                posizioneBottone = Integer.parseInt(v.getTag().toString());

                intent = new Intent( context, EditActivity1.class);

                // Log.d("LOG" , "Il Tag cliccato è " + posizioneBottone );
                Log.d("PosizioneBottone" , mess1.get(posizioneBottone)+"---> pos ->"+posizioneBottone);
                String[] lines= mess1.get(posizioneBottone).split("\\n");

                String risorsa=lines[0];
                String indirizzo=lines[1];
                String volontari=lines[2];
                String num=volontari.substring(volontari.length()-1);
                String protezione=lines[3];
                String num1=protezione.substring(protezione.length()-1);
                Bundle mBundle = new Bundle();
                //mBundle.putString("key",mess1.get(posizioneBottone));
                mBundle.putString("key1",risorsa);
                mBundle.putString("key",indirizzo);
                mBundle.putString("key2",num);
                mBundle.putString("key3",num1);
                //mBundle.putInt("pos", pp);

                intent.putExtras(mBundle);
                intent.putExtra("ora",ora);

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

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                //  builder.setTitle("Confirm dialog demo !");
                builder.setMessage("Sei sicuro di voler eliminare questo intervento?");
                builder.setCancelable(false);
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mess1.remove(posizioneDlt);
                        notifyDataSetChanged();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(context, "You've changed your mind to delete all records", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();


            }
        });


        return v;
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();
                String result=b.getString("key2");
                String result1=b.getString("key1");
                String result2=b.getString("key3");
                String result3=b.getString("key4");


                mess1.remove(posizioneBottone);
                mess1.add(posizioneBottone,result + "\n" + result1 + "\n" + "N. volontari: " + result2 + "\n" + "Protezione Civile: " + result3);
                notifyDataSetChanged();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                // builder.setTitle("Alert dialog demo !");
                builder.setMessage("Modifica avvenuta con successo");
                builder.setCancelable(true);
                builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                //String i = Integer.toString(pos2);

                //Toast.makeText(context ,mess.get(pos2),Toast.LENGTH_SHORT).show();
            }

        }

    }



}


