package com.example.sara.navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import java.util.ArrayList;

public class MainActivityRegistro extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ListView myListView;
    ArrayList<String> mess = new ArrayList<String>();
    ArrayList<String> date= new ArrayList<String>();
    ArrayList<String> ora= new ArrayList<String>();
    String[] ciao;
    ItemAdapterRegistro itemAdapter;

    Intent intent;

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
        setContentView(R.layout.activity_main_registro);



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


        Resources res= getResources();
        myListView= (ListView) findViewById(R.id.myListView);

        String[] ciao = res.getStringArray(R.array.mexR) ;
        for (int i = 0; i < ciao.length; i++ ){
            mess.add(i,ciao[i]);


        }
        ciao = res.getStringArray(R.array.data) ;
        for (int i = 0; i < ciao.length; i++ ){
            date.add(i,ciao[i]);

        }
        ciao = res.getStringArray(R.array.ora) ;
        for (int i = 0; i < ciao.length; i++ ){
            ora.add(i,ciao[i]);

        }



        itemAdapter = new ItemAdapterRegistro(this, mess, date, ora);
        myListView.setAdapter(itemAdapter);
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
}
