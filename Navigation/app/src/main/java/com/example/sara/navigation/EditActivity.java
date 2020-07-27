package com.example.sara.navigation;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sara on 23/06/2018.
 */
public class EditActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ArrayList<String> risorse=new ArrayList<String>();
    ArrayList<String> quantita=new ArrayList<String>();

    Bundle b;
    ListView myListView;
    Editable edit1,edit2,edit3;
    TextView text;
    Bundle mBundle;
    int pos,j;
    Spinner spinner;
    EditText ed5,edit,ed1;
    Button b1,button;
    FloatingActionButton b2;
    AdapterItem itemAdapter;
    String q,n;
    Intent intent2;
    Intent intent;

    public static final String PREFS_NAME="MyPrefsFile";
    public static final String PREFS_USERNAME="username";
    public static final String PREFS_PASSWORD="password";

    String loginID;
    String loginPWD;
    TextView t1;
    DrawerLayout drawer;
    Toolbar toolbar=null;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        loginID = prefs.getString(PREFS_USERNAME,null);

        loginPWD = prefs.getString(PREFS_PASSWORD,null);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        t1=(TextView) hView.findViewById(R.id.nome);
        t1.setText(loginID);
        navigationView.setNavigationItemSelectedListener(this);

        Resources res= getResources();
        myListView= (ListView) findViewById(R.id.myListView);

        intent = getIntent();
         mBundle = intent.getExtras();

        risorse= mBundle.getStringArrayList("mess");
        quantita= mBundle.getStringArrayList("q");


        button = (Button) findViewById(R.id.conferma);
        b2 = (FloatingActionButton) findViewById(R.id.addBtn);
        b1 = (Button) findViewById(R.id.confermaBtn);





       /* String[] ciao = res.getStringArray(R.array.risorse) ;
        for (int i = 0; i < ciao.length; i++ ){
            risorse.add(i,ciao[i]);

        }*/

   /*     String[] quantity = res.getStringArray(R.array.quantita) ;
        for (int i = 0; i < quantity.length; i++ ){
            quantita.add(i,quantity[i]);
        }*/

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ed5.setVisibility(View.VISIBLE);
               // ed1.setVisibility(View.VISIBLE);

               // Bundle b = new Bundle();

               // String risorsa = ed5.getText().toString();
               // String numero = ed1.getText().toString();
               // b.putString("risorsa",risorsa);
               // b.putString("quantita",numero);
              //  intent.putExtras(b);
              //  setResult(RESULT_OK, intent);
               // finish();
               Intent intent= new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent, 2);
            }
        });


        itemAdapter = new AdapterItem(this, risorse,quantita);

        myListView.setAdapter(itemAdapter);

         edit=(EditText) findViewById(R.id.editText);
       // q=edit.getText().toString();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                             //  quantita.remove();
                mBundle.putStringArrayList("r",risorse);
                mBundle.putStringArrayList("q",quantita);
                intent.putExtras(mBundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        intent = new Intent();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if (id == R.id.nav_camera) {
            intent.setClass(this, Meteo.class);
            startActivity(intent);
            finish();
        }
        if (id == R.id.nav_logout) {
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
            finish();

        }  if (id == R.id.resource) {
            intent.setClass(this, VisualizzaRisorse.class);
            startActivity(intent);
            finish();

        }
        if (id == R.id.nav_gallery) {

            intent.setClass(this, MainActivityRegistro.class);
            startActivity(intent);
            finish();

        }  if (id == R.id.nav_manage) {
            intent.setClass(this, TabellaInterventi.class);
            startActivity(intent);
            finish();


        }
        if (id == R.id.nav_share) {
            if (loginID.equals("sindaco")){
                intent.setClass(this, NotificheSindaco.class);
                startActivity(intent);
                finish();
            }else{
                intent.setClass(this, NotificheVolontario.class);
                startActivity(intent);
                finish();
            }


        }
        if (id == R.id.nav_send) {
            intent.setClass(this, MainActivityAvvisi.class);
            startActivity(intent);
            finish();

        }
        return true;
    }
    //per add activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        itemAdapter.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 0)
        {
            if(resultCode == Activity.RESULT_OK) {

                Bundle b = data.getExtras();
                String result=b.getString("s");
                 //j=b.getInt("p");

               // String jj=Integer.toString(j);
               // Toast.makeText(this,jj,Toast.LENGTH_SHORT).show();

                quantita.remove(itemAdapter.posizioneBottone);

                quantita.add(itemAdapter.posizioneBottone,result);
                // Toast.makeText(context,result,Toast.LENGTH_SHORT).show();

            }
            }
        if(requestCode == 2){
            if(resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();
                String result=b.getString("risorsa");
                String result1=b.getString("quantita");


                 risorse.add(result);
                quantita.add(result1);


                AlertDialog.Builder builder = new AlertDialog.Builder(this);
               // builder.setTitle("Alert dialog demo !");
                builder.setMessage("Modifica avvenuta con successo");
                builder.setCancelable(true);
                builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      //  Toast.makeText(getApplicationContext(), "Neutral button clicked", Toast.LENGTH_SHORT).show();


                        /*Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                        intent.putStringArrayListExtra("r",risorse);
                        intent.putStringArrayListExtra("q",quantita);
                        startActivityForResult(intent, 2);
*/

                    }
                });
                builder.show();

                itemAdapter.notifyDataSetChanged();

            }
        }

    }






}
