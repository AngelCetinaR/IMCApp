package com.angelcetina.android.imcapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextPeso;
    private EditText mEditTextEstatura;
    private Button mButtonCalcular;
    private Button mButtonLimpiar;
    private TextView mTextViewImc;
    private TextView mTextViewEstado;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextPeso = (EditText) findViewById(R.id.edit_text_peso);
        mEditTextEstatura = (EditText) findViewById(R.id.edit_text_estatura);
        mButtonCalcular = (Button) findViewById(R.id.button_calcular);
        mTextViewImc = (TextView) findViewById(R.id.text_view_imc);
        mButtonLimpiar = (Button) findViewById(R.id.button_limpiar);
        mTextViewEstado = (TextView) findViewById(R.id.text_view_nutricional);

        mButtonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditTextPeso.setText("");
                mEditTextEstatura.setText("");
                mTextViewImc.setText("");
                mTextViewEstado.setText("");
                double peso = 0, estatura = 0;
            }
        });

        mButtonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try { if (mEditTextPeso.length()>0 && mEditTextEstatura.length()>0){
                    String s = mEditTextPeso.getText().toString();
                    double peso = Double.parseDouble(s);
                    s = mEditTextEstatura.getText().toString();
                    double estatura = Double.parseDouble(s);
                    DecimalFormat df = new DecimalFormat("0.000");
                    double imc = peso / (estatura * estatura);
                    mTextViewImc.setText(df.format(Double.toString(imc)));

                    if (imc <= 18.49){
                        mTextViewEstado.setText("Desnutrición");
                    }else if (imc <= 24.99){
                        mTextViewEstado.setText("Peso Normal");
                    }else if (imc <=29.99){
                        mTextViewEstado.setText("Sobrepeso");
                    }else if (imc <= 39.99){
                        mTextViewEstado.setText("Obesidad");
                    }else if (imc >= 40){
                        mTextViewEstado.setText("Obesidad extrema");
                    }
                }

                }catch (NumberFormatException w){
                    mTextViewEstado.setText(w.getMessage());
                }



            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.angelcetina.android.imcapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.angelcetina.android.imcapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
