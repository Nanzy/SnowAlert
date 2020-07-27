package com.example.sara.navigation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView myListView;

    ArrayList<String> mess = new ArrayList<String>();

    Button conferma;
    FloatingActionButton addBtn;
    ItemAdapter2 itemAdapter;
    Intent intent,intent1, intt;

    TextView t;

    int total;

    Bundle b, b1;
    String ora;
    android.support.v7.widget.Toolbar toolbar=null;
    public static final String PREFS_NAME="MyPrefsFile";
    public static final String PREFS_USERNAME="username";
    public static final String PREFS_PASSWORD="password";

    String loginID;
    String loginPWD;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        loginID = prefs.getString(PREFS_USERNAME,null);

        loginPWD = prefs.getString(PREFS_PASSWORD,null);

        //Log.d("USERNAME", loginID);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        t1=(TextView) hView.findViewById(R.id.nome);
        t1.setText(loginID);
        navigationView.setNavigationItemSelectedListener(this);

        // Resources res= getResources();
        myListView= (ListView) findViewById(R.id.myListView);

       /* String[] ciao = res.getStringArray(R.array.mex) ;
        for (int i = 0; i < ciao.length; i++ ){
            mess.add(i,ciao[i]);
            // Log.d("Main", "IL mEssaggio e :" + mess.get(i));

        }*/
        t=(TextView) findViewById(R.id.textView6); //fascia oraria



        intt = getIntent();
        b = intt.getExtras();
        mess= b.getStringArrayList("mess");
        ora=b.getString("ora");
        t.setText(ora);
        t.setGravity(Gravity.CENTER);
        t.setTextSize(30);

       // mess=b.getStringArrayList("mess");
        itemAdapter = new ItemAdapter2(this, mess);

        myListView.setAdapter(itemAdapter);

      /*  intent1=getIntent();
        b=intent1.getExtras();

        if (b != null) {

            ArrayList<String> value = b.getStringArrayList("mess");

            itemAdapter=new ItemAdapter2(getApplicationContext(),value);
            myListView.setAdapter(itemAdapter);
        }*/



        addBtn = (FloatingActionButton) findViewById(R.id.addBtn);

        conferma = (Button) findViewById(R.id.confermaBtn);

        if(!(loginID.equals("sindaco"))){
            addBtn.setVisibility(View.INVISIBLE);
            conferma.setVisibility(View.INVISIBLE);
        }


      //  t.setText("09:00-10:00");




        /*Bundle extras = getIntent().getExtras();
        if (extras != null) {

            String value=extras.getString("ora");

            t.setText(value);
            t.setGravity(Gravity.LEFT);


        }*/

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent= new Intent(getApplicationContext(), AddActivity1.class);
                intent.putExtra("ora",ora);
                startActivityForResult(intent, 2);



            }
        });

        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // intent= new Intent(getApplicationContext(), TabellaInterventi.class);

               // Toast.makeText(getApplicationContext(), "Total number of Items are:" + myListView.getAdapter().getCount() , Toast.LENGTH_LONG).show();
                 b1=new Bundle();
                intent1=getIntent();

                 total=myListView.getAdapter().getCount();
                 b1.putInt("tot",total);
                b1.putStringArrayList("m",mess);

//                b.putInt("totale",total);
            //    startActivityForResult(intent, 3);

                intent1.putExtras(b1);
                setResult(RESULT_OK, intent1);
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
            itemAdapter.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2){
            if(resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();
                String result=b.getString("risorsa");
                String result1=b.getString("indirizzo");
                String result2=b.getString("volontari");
                String result3=b.getString("protezione");


                //int j=mess.size();


                if(result3.equals("") && result2.equals(""))
                {

                    mess.add(result + "\n" + result1 + "\n" + "N. volontari: 0" + "\n" + "Protezione Civile: 0" );

                }
                else if(result2 != "" && result3.equals("")) {
                    mess.add(result + "\n" + result1 + "\n" + "N. volontari: "+ result2 + "\n" + "Protezione Civile: 0" );

                }
                else if(result3 != "" && result2.equals("")  ) {
                    mess.add(result + "\n" + result1 + "\n" + "N. volontari: 0" + "\n" + "Protezione Civile: " + result3);

                }
                else if (result2 != "" && result3 != "")
                {
                    mess.add(result + "\n" + result1 + "\n" + "N. volontari: "+ result2 + "\n" + "Protezione Civile: " + result3);

                }


                itemAdapter.notifyDataSetChanged();

            }
        }




    }


}