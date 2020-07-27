package com.example.sara.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sara on 23/06/2018.
 */
public class AdapterItem extends BaseAdapter {

    LayoutInflater mInflater;
    ArrayList<String> mess1= new ArrayList<String>();

    ArrayList<String> quantita= new ArrayList<String>();


    String messaggio;
    String quantity;

    Intent intent;
    TextView messText, qText;
    Context context;
    EditText ed5,ed,ed1;
    Button b1;
    FloatingActionButton button1;


    Button button ;
    FloatingActionButton deleteButton;

   String posizioneBottone1;
    int posizioneBottone, posizioneDlt;


    public AdapterItem(Context c, ArrayList<String> m, ArrayList<String> q) {
        mess1=m;
        quantita=q;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.list_details, null);
        messText = (TextView) v.findViewById(R.id.mexText);
        button1 = (FloatingActionButton) v.findViewById(R.id.editBtn);

        qText = (TextView) v.findViewById(R.id.textView); //per modificare quantità



        deleteButton = (FloatingActionButton) v.findViewById(R.id.deleteBtn);




        Log.d("count" , "Il numero è " + getCount());
        messaggio = mess1.get(position);
        quantity=quantita.get(position);

        button1.setTag(position);

       // qText.setTag(position);
        deleteButton.setTag(position);

        messText.setText(messaggio);
        qText.setText(quantity);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                posizioneBottone = Integer.parseInt(v.getTag().toString());
                posizioneBottone1 =v.getTag().toString();

                intent = new Intent( context, Modifica.class);


                Bundle mBundle = new Bundle();
                mBundle.putString("key",mess1.get(posizioneBottone));
                mBundle.putString("num", quantita.get(posizioneBottone));

                //mBundle.putInt("pos", pp);

                intent.putExtras(mBundle);

                //Toast.makeText((Activity)context, Integer.toString(o),Toast.LENGTH_SHORT).show();
                ((Activity) context).startActivityForResult(intent, 0);

            }
        });

      /*  qText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.charAt(s.length() - 1) == '\n') {
                    Log.d("TEST RESPONSE", "Enter was pressed");
                }
            }
        });*/

/*        if(qText.isSelected()) {

            posizioneBottone = Integer.parseInt(v.getTag().toString());

            //intent = new Intent( context, MainActivity.class);
            intent = new Intent(context, EditActivity.class);
            Bundle mBundle = new Bundle();
            String num = qText.getText().toString();
            mBundle.putInt("pos", posizioneBottone);
            mBundle.putString("key", num);


            intent.putExtras(mBundle);

            ((Activity) context).startActivity(intent);


        }*/


       deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                posizioneDlt = Integer.parseInt(v.getTag().toString());


                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
              //  builder.setTitle("Confirm dialog demo !");
                builder.setMessage("Sei sicuro di voler eliminare questa risorsa?");
                builder.setCancelable(false);
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(context, "You've choosen to delete all records", Toast.LENGTH_SHORT).show();
                        mess1.remove(posizioneDlt);
                        quantita.remove(posizioneDlt);
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


//collegato a quello di edit
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if(resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();
                String result=b.getString("s");
                //b.putInt("p",posizioneBottone);

                quantita.remove(posizioneBottone);

                quantita.add(posizioneBottone,result);
               // Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();


            }

        }
    }



}


