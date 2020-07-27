package com.example.sara.navigation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class MainActivityAvvisi extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ListView myListView;
    ArrayList<String> mess = new ArrayList<String>();
    ArrayList<String> date= new ArrayList<String>();
    ArrayList<String> ora= new ArrayList<String>();
    FloatingActionButton addBtn;
    ItemAdapterAvvisi itemAdapter;
    Intent intent;
    Intent mIntent = new Intent();
    Bundle b3 = null;



    android.support.v7.widget.Toolbar toolbar=null;



    public static final String PREFS_NAME="MyPrefsFile";
    public static final String PREFS_USERNAME="username";
    public static final String PREFS_PASSWORD="password";

    String loginID;
    String loginPWD;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_avvisi);

        Resources res= getResources();
            String[] ciao = res.getStringArray(R.array.mexx) ;

            for (int i = 0; i < ciao.length; i++ ){
                mess.add(i,ciao[i]);
                // Log.d("Main", "IL mEssaggio e :" + mess.get(i));

            }
            ciao = res.getStringArray(R.array.data) ;
            for (int i = 0; i < ciao.length; i++ ){
                date.add(i,ciao[i]);
                // Log.d("Main", "IL mEssaggio e :" + data.get(i));
            }
            ciao = res.getStringArray(R.array.ora) ;
            for (int i = 0; i < ciao.length; i++ ){
                ora.add(i,ciao[i]);
                // Log.d("Main", "IL mEssaggio e :" + ora.get(i));
            }




      /**  if (savedInstanceState != null) {
            //super.onCreate(savedInstanceState);
            // Restore value of members from saved state
            mess =  savedInstanceState.getStringArrayList("mess");
            date =  savedInstanceState.getStringArrayList("data");
            ora =  savedInstanceState.getStringArrayList("ora");
        } else {
            // Probably initialize members with default values for a new instance
           // super.onCreate(savedInstanceState);
            Resources res= getResources();
            String[] ciao = res.getStringArray(R.array.mexx) ;

            for (int i = 0; i < ciao.length; i++ ){
                mess.add(i,ciao[i]);
                // Log.d("Main", "IL mEssaggio e :" + mess.get(i));

            }
            ciao = res.getStringArray(R.array.data) ;
            for (int i = 0; i < ciao.length; i++ ){
                date.add(i,ciao[i]);
                // Log.d("Main", "IL mEssaggio e :" + data.get(i));
            }
            ciao = res.getStringArray(R.array.ora) ;
            for (int i = 0; i < ciao.length; i++ ){
                ora.add(i,ciao[i]);
                // Log.d("Main", "IL mEssaggio e :" + ora.get(i));
            }
        }
**/


        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        loginID = prefs.getString(PREFS_USERNAME,null);

        loginPWD = prefs.getString(PREFS_PASSWORD,null);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        t=(TextView) hView.findViewById(R.id.nome);
        t.setText(loginID);
        navigationView.setNavigationItemSelectedListener(this);



        myListView= (ListView) findViewById(R.id.myListView);



        itemAdapter = new ItemAdapterAvvisi(this, mess, date, ora);
        myListView.setAdapter(itemAdapter);

        addBtn = (FloatingActionButton) findViewById(R.id.addBtn);


       /** if(!(loginID.equals("sindaco"))){
            addBtn.setVisibility(View.INVISIBLE);
        }**/


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent= new Intent(getApplicationContext(), AddActivityAvvisi.class);
                startActivityForResult(intent, 2);



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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       itemAdapter.onActivityResult(requestCode, resultCode, data);
       if(requestCode == 2){
           if(resultCode == Activity.RESULT_OK){
               Bundle b = data.getExtras();
               String result=b.getString("avviso");

               //Log.d("PosizioneBottone2222" , result+"---> pos ->"+posizioneBottone);
               mess.add(0, result);


               Date currentTime = Calendar.getInstance().getTime();
              // Log.d("TEMPO", "IL TEMPO e :" + currentTime);
               //DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DATE_FIELD, 1,Locale.UK);
               String mDate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(currentTime);
               date.add(0, mDate);

               DateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ITALY);
               String mOra = sdf.format(currentTime);
               ora.add(0, mOra);

               itemAdapter.notifyDataSetChanged();
               Toast.makeText(this ,"Avviso aggiunto con successo", Toast.LENGTH_SHORT).show();

               }
       }

    }


  /** @Override
   public void onSaveInstanceState(Bundle savedInstanceState) {
       super.onSaveInstanceState(savedInstanceState);
       savedInstanceState.putStringArrayList("mess",mess);
       savedInstanceState.putStringArrayList("data",date);
       savedInstanceState.putStringArrayList("ora",ora);
       // Always call the superclass so it can save the view hierarchy state

   }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        mess = savedInstanceState.getStringArrayList("mess");
        date =  savedInstanceState.getStringArrayList("data");
        ora =  savedInstanceState.getStringArrayList("ora");

    }
**/

/**
   public void onDestroy(Bundle savedInstanceState){
     super.onDestroy();
       savedInstanceState.putStringArrayList("mess",mess);
       savedInstanceState.putStringArrayList("data",date);
       savedInstanceState.putStringArrayList("ora",ora);

   }

    public void onStop(){
        super.onStop();

        Bundle b = new Bundle();

        b.putStringArrayList("mess",mess);
        b.putStringArrayList("data",date);
        b.putStringArrayList("ora",ora);
        mIntent.putExtras(b);



    }

    public void onPause(){
        super.onPause();

        Bundle b = new Bundle();

        b.putStringArrayList("mess",mess);
        b.putStringArrayList("data",date);
        b.putStringArrayList("ora",ora);
        mIntent.putExtras(b);



    }

    public void onResume(){
        super.onResume();
        Bundle b2 =mIntent.getExtras();
        mess= b2.getStringArrayList("mess");
        date= b2.getStringArrayList("data");
        ora= b2.getStringArrayList("ora");

    }**/


}