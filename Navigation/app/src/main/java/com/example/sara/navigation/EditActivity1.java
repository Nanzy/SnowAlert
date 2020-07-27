package com.example.sara.navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Sara on 23/06/2018.
 */
public class EditActivity1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Editable edit1,edit2,edit3;
    TextView t;
    Bundle mBundle;
    int pos;
    Spinner spinner;
    EditText ed5,ed,ed1;
    Button b1,conferma;

    Intent intent;

    android.support.v7.widget.Toolbar toolbar=null;
    public static final String PREFS_NAME="MyPrefsFile";
    public static final String PREFS_USERNAME="username";
    public static final String PREFS_PASSWORD="password";

    String loginID;
    String loginPWD;
    TextView t1;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit1);

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
        t1=(TextView) hView.findViewById(R.id.nome);
        t1.setText(loginID);
        navigationView.setNavigationItemSelectedListener(this);



        final Intent intent = getIntent();
        mBundle = intent.getExtras();
        text=mBundle.getString("ora");
        t=(TextView) findViewById(R.id.textView4);
        t.setText(text);
        t.setGravity(Gravity.CENTER);
        t.setTextSize(30);

        //String value = intent.getStringExtra("key");
        String value = mBundle.getString("key");
        String value1 = mBundle.getString("key1");
        String value2 = mBundle.getString("key2");
        String value3 = mBundle.getString("key3");

        Log.d("Intent", "IL mEssaggio e :" +value);
        spinner = (Spinner) findViewById(R.id.spinner2);
        ed5 = (EditText) findViewById(R.id.editText5);
        ed = (EditText) findViewById(R.id.editText);
        ed1 = (EditText) findViewById(R.id.editText1);
        b1 = (Button) findViewById(R.id.conferma);


        spinner.setSelection(getIndex(spinner, value1));

        ed5.setText(value);
        ed.setText(value2);
        ed1.setText(value3);



        conferma = (Button) findViewById(R.id.conferma);
        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 edit1=ed5.getText();
                edit2=ed1.getText();
                edit3=ed.getText();
               String element=spinner.getSelectedItem().toString();

                String valore= edit1.toString();
                String valore1= edit2.toString();
                String valore2= edit3.toString();
                String valore3= element.toString();

                mBundle.putString("key2", valore3);
                mBundle.putString("key1", valore);
                mBundle.putString("key3", valore2);
                mBundle.putString("key4", valore1);

                intent.putExtras(mBundle);
                setResult(RESULT_OK, intent);
                finish();

                //Intent in = new Intent(getApplicationContext(), ItemAdapter.class);
                //Bundle mBundle = new Bundle();
                //mBundle.putString("key2", value);
                //in.putExtras(mBundle);

                // intent.putExtra("keyy", editMex);
                //setResult(RESULT_OK, intent);
                //startActivity(in);
                // finish();





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
            finish();

        }
        if (id == R.id.nav_send) {
            intent.setClass(this, MainActivityAvvisi.class);
            startActivity(intent);
            finish();

        }
        return true;
    }
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }


}
