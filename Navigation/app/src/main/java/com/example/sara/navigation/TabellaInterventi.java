package com.example.sara.navigation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sara on 23/06/2018.
 */
public class TabellaInterventi extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ImageButton zoom,zoom1;
    TextView numero,ora;
    Intent i,i2, intentInterventi;
    Bundle b = new Bundle();
    Bundle bMap = new Bundle();
    int righe, result;
    //ImageButton mappa;
    FloatingActionButton mappa;
    ArrayList<String> mess = new ArrayList<String>();
    android.support.v7.widget.Toolbar toolbar=null;

    public static final String PREFS_NAME="MyPrefsFile";
    public static final String PREFS_USERNAME="username";
    public static final String PREFS_PASSWORD="password";

    String loginID;
    String loginPWD;
    TextView t;

    Intent intent;
    FrameLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sintesi_interventi);

        //content = (FrameLayout) findViewById(R.id.nav_view);

        //getLayoutInflater().inflate(R.layout.sintesi_interventi, content);

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


        zoom=(ImageButton) findViewById(R.id.zoom);
        zoom1=(ImageButton) findViewById(R.id.zoom1);
        numero=(TextView) findViewById(R.id.numero);

        mappa=(FloatingActionButton) findViewById(R.id.mappaBtn);

        ora=(TextView) findViewById(R.id.ora);

        // i=getIntent();
       // b=i.getExtras();
       // int result=b.getInt("totale");
       // System.out.println("totale: " + result);


        numero.setText("N. interventi: 1" );

        Resources res= getResources();

        String[] ciao = res.getStringArray(R.array.mex) ;
        for (int i = 0; i < ciao.length; i++ ){
            mess.add(i,ciao[i]);
            // Log.d("Main", "IL mEssaggio e :" + mess.get(i));

        }

        zoom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                i=new Intent(TabellaInterventi.this,MainActivity1.class);

              //  String ora1=ora.getText().toString();
              //  System.out.println("prova " + ora1);


                b.putStringArrayList("mess",mess);
                b.putString("ora",ora.getText().toString());
                i.putExtras(b);
              //  b.putString("ora",ora1);
                //startActivity(new Intent(TabellaInterventi.this,MainActivity1.class));
                startActivityForResult(i,0);
                //numero.setText("N. interventi: " + righe);
            }
        });

        mappa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                i2=new Intent(TabellaInterventi.this,MapsActivity.class);
                bMap.putStringArrayList("mess",mess);
                i2.putExtras(bMap);
                startActivity(i2);
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


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if(resultCode == Activity.RESULT_OK){
                b = data.getExtras();
                result=b.getInt("tot");
                mess=b.getStringArrayList("m");


                numero.setText("N. interventi: " + result);
               // righe=result;

            }

        }
    }
}
