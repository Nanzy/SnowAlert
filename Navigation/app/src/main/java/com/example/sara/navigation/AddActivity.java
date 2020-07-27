package com.example.sara.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Sara on 23/06/2018.
 */
public class AddActivity extends AppCompatActivity {

    Intent intent;

    EditText addText;
    Button conferma;
    String text;
    Bundle b = new Bundle();

    EditText ed5,ed,ed1;
    Button b1;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_risorsa);

        intent= getIntent();

        ed5 = (EditText) findViewById(R.id.editText5);

        ed1 = (EditText) findViewById(R.id.editText1);
        b1 = (Button) findViewById(R.id.conferma);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String risorsa = ed5.getText().toString();
                String numero = ed1.getText().toString();
                b.putString("risorsa",risorsa);
                b.putString("quantita",numero);

                intent.putExtras(b);
                setResult(RESULT_OK, intent);
                finish();



            }

        });






    }
}

