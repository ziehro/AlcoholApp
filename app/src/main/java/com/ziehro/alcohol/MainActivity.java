package com.ziehro.alcohol;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.lang.Math;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    EditText alc, vol, quantity, price;
    TextView TVA, alcPerBuck;
    Button buttonSubmit, buttonReset;
    Double tvaResult, alcPerBuckResult, alcNum, volNum, quantityNum, priceNum;
    AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //AdMOB

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        alc = (EditText) findViewById(R.id.alc);
        vol = (EditText) findViewById(R.id.vol);
        quantity = (EditText) findViewById(R.id.quantity);
        price = (EditText) findViewById(R.id.price);
        TVA = (TextView) findViewById(R.id.TVA);
        alcPerBuck = (TextView) findViewById(R.id.alcPerBuck);
        buttonSubmit = (Button) findViewById(R.id.buttonCalc);
        //buttonReset = (Button) findViewById(R.id.buttonReset);
        /*
            Submit Button
        */
        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                volNum = Double.valueOf(vol.getText().toString());
                alcNum = Double.valueOf(alc.getText().toString());
                quantityNum = Double.valueOf(quantity.getText().toString());
                priceNum = Double.valueOf(price.getText().toString());


                tvaResult = (volNum/100 * alcNum * quantityNum);
                alcPerBuckResult = tvaResult/priceNum;

                String tva = String.format("%.2f", tvaResult);
                String apbr = String.format("%.2f", alcPerBuckResult);
                //alcPerBuckResult = Float.valueOf(Math.round(alcPerBuckResult));

                TVA.setText(tva);
                if (alcPerBuckResult < 1000) alcPerBuck.setTextColor(getResources().getColor(R.color.Green));
                if (alcPerBuckResult < 11) alcPerBuck.setTextColor(getResources().getColor(R.color.Yellow));
                if (alcPerBuckResult < 8) alcPerBuck.setTextColor(getResources().getColor(R.color.Red));
                alcPerBuck.setText(apbr);



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}