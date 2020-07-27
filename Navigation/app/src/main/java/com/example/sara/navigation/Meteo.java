package com.example.sara.navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.function.Function;

public class Meteo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    Intent intent;

    Toolbar toolbar=null;
    NavigationView navigationView;
    public static final String PREFS_NAME="MyPrefsFile";
    public static final String PREFS_USERNAME="username";
    public static final String PREFS_PASSWORD="password";
    String loginID;
    String loginPWD;
    TextView t;

    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField,updatedField1;
    Typeface weatherFont;
    Button domani,oggi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        loginID = prefs.getString(PREFS_USERNAME,null);

        loginPWD = prefs.getString(PREFS_PASSWORD,null);



/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        t=(TextView) hView.findViewById(R.id.nome);
        t.setText(loginID);
        navigationView.setNavigationItemSelectedListener(this);


        weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        cityField = (TextView)findViewById(R.id.city_field);
        updatedField = (TextView)findViewById(R.id.updated_field);
        //  updatedField1 = (TextView)findViewById(R.id.updated_field1);

        detailsField = (TextView)findViewById(R.id.details_field);
        currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
        humidity_field = (TextView)findViewById(R.id.humidity_field);
        pressure_field = (TextView)findViewById(R.id.pressure_field);
        weatherIcon = (TextView)findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);


        com.example.sara.navigation.Function.placeIdTask asyncTask =new com.example.sara.navigation.Function.placeIdTask(new com.example.sara.navigation.Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                cityField.setText(weather_city);
                updatedField.setText(weather_updatedOn);
                //    updatedField1.setText(weather_updatedOn);
                detailsField.setText(weather_description);
                currentTemperatureField.setText(weather_temperature);
                humidity_field.setText("Umidità: "+weather_humidity);
                pressure_field.setText("Pressione: "+weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));
                //Log.d("03 lug 2018 12:55:00 PM", weather_updatedOn);



                //Log.d("domani",gc.getTime().toString());



            }
        });
        asyncTask.execute("40.773181", "14.797095"); //  asyncTask.execute("Latitude", "Longitude")

        domani = (Button) findViewById(R.id.domani);
        oggi = (Button) findViewById(R.id.oggi);

        oggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                com.example.sara.navigation.Function.placeIdTask asyncTask =new com.example.sara.navigation.Function.placeIdTask(new com.example.sara.navigation.Function.AsyncResponse() {
                    public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                        cityField.setText(weather_city);
                        updatedField.setText(weather_updatedOn);
                        //    updatedField1.setText(weather_updatedOn);
                        detailsField.setText(weather_description);
                        currentTemperatureField.setText(weather_temperature);
                        humidity_field.setText("Umidità: "+weather_humidity);
                        pressure_field.setText("Pressione: "+weather_pressure);
                        weatherIcon.setText(Html.fromHtml(weather_iconText));

                    }
                });
                asyncTask.execute("40.773181", "14.797095"); //  asyncTask.execute("Latitude", "Longitude")

            }
        });


        domani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                com.example.sara.navigation.Function.placeIdTask asyncTask =new com.example.sara.navigation.Function.placeIdTask(new com.example.sara.navigation.Function.AsyncResponse() {
                    public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                        cityField.setText(weather_city);

                        Calendar calendar = Calendar.getInstance();
                        Date today = calendar.getTime();

                        calendar.add(Calendar.DAY_OF_YEAR, 1);
                        Date tomorrow = calendar.getTime();

                        DateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss aaa", Locale.ITALY);
                        String t = sdf.format(tomorrow);
                        updatedField.setText(t);


                        //    updatedField1.setText(weather_updatedOn);
                        weather_description="SUNNY";
                        detailsField.setText(weather_description);

                        weather_temperature="27,55°";
                        currentTemperatureField.setText(weather_temperature);

                        weather_humidity="59%";
                        humidity_field.setText("Umidità: "+weather_humidity);
                        weather_pressure="980 hPa";

                        pressure_field.setText("Pressione: "+weather_pressure);
                        weatherIcon.setText(Html.fromHtml("&#xf00d;"));

                    }
                });
                asyncTask.execute("40.773181", "14.797095"); //  asyncTask.execute("Latitude", "Longitude")

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
        // Handle navigation view item clicks here.
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
