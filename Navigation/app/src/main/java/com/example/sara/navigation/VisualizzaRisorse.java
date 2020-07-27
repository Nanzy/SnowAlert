package com.example.sara.navigation;

/**
 * Created by Sara on 28/06/2018.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;



import java.util.ArrayList;


public class VisualizzaRisorse extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    GridView myListView,myListView1;
    ArrayList<String> mess = new ArrayList<String>();
    ArrayList<String> q = new ArrayList<String>();

    String[] quant;

    Button modifica;
    ItemAdapter itemAdapter;
    ItemAdapter1 itemAdapter1;
    Intent intent, intent1, intentInterventi;

    Bundle b;
    public static final String PREFS_NAME="MyPrefsFile";
    public static final String PREFS_USERNAME="username";
    public static final String PREFS_PASSWORD="password";

    String loginID;
    String loginPWD;

    TextView t1;
    DrawerLayout drawer;
    Toolbar toolbar=null;
    NavigationView navigationView;

    FrameLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        //content = (FrameLayout) findViewById(R.id.nav_view);

        //getLayoutInflater().inflate(R.layout.activity_main1, content);

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
        myListView= (GridView) findViewById(R.id.myListView);
        myListView1= (GridView) findViewById(R.id.myListView1);




        String[]  ciao = res.getStringArray(R.array.risorse) ;
        for (int i = 0; i < ciao.length; i++ ){
            mess.add(i,ciao[i]);


        }

        quant = res.getStringArray(R.array.quantita) ;
        for (int i = 0; i < quant.length; i++ ){
            q.add(i,quant[i]);

        }

    
        itemAdapter = new ItemAdapter(this, mess);
        itemAdapter1=new ItemAdapter1(this,q);

        myListView.setAdapter(itemAdapter);
        myListView1.setAdapter(itemAdapter1);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            ArrayList<String> value = extras.getStringArrayList("r");
            ArrayList<String> value1 = extras.getStringArrayList("q");

            itemAdapter=new ItemAdapter(getApplicationContext(),value);
            itemAdapter1=new ItemAdapter1(getApplicationContext(),value1);
            myListView.setAdapter(itemAdapter);
            myListView1.setAdapter(itemAdapter1);
        }



        modifica = (Button) findViewById(R.id.confermaBtn);


        if(!(loginID.equals("sindaco"))){
            modifica.setVisibility(View.INVISIBLE);
        }



        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                intent1= new Intent(getApplicationContext(), EditActivity.class);

                b=new Bundle();
                b.putStringArrayList("q",q);
                b.putStringArrayList("mess",mess);
                intent1.putExtras(b);

                startActivityForResult(intent1,0);

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0){
            if(resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();

                mess=b.getStringArrayList("r");
                q=b.getStringArrayList("q");

                itemAdapter=new ItemAdapter(this,mess);
                itemAdapter1=new ItemAdapter1(this,q);

                myListView.setAdapter(itemAdapter);
                myListView1.setAdapter(itemAdapter1);


                itemAdapter.notifyDataSetChanged();
                itemAdapter1.notifyDataSetChanged();


            }
        }

    }


}
