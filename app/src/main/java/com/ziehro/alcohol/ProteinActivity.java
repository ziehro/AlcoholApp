package com.ziehro.alcohol;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ProteinActivity extends AppCompatActivity {

    EditText protein, perGramOrML, fullSize, price;
    TextView TVA, ProteinPerBuck;
    Button buttonSubmit, buttonGoToMain;
    Double tvpResult, ProteinPerBuckResult, proteinNum, perGrams, fullSizeNum, priceNum;
    AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protein);
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

        protein = (EditText) findViewById(R.id.proteinPercent);
        perGramOrML = (EditText) findViewById(R.id.perGramOrML);
        fullSize = (EditText) findViewById(R.id.fullSize);
        price = (EditText) findViewById(R.id.price);
        TVA = (TextView) findViewById(R.id.TVA);
        ProteinPerBuck = (TextView) findViewById(R.id.alcPerBuck);
        buttonSubmit = (Button) findViewById(R.id.buttonCalc);
        buttonGoToMain = (Button) findViewById(R.id.goToMainBtn);
        /*
            Submit Button
        */
        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                perGrams = Double.valueOf(perGramOrML.getText().toString());
                proteinNum = Double.valueOf(protein.getText().toString());
                fullSizeNum = Double.valueOf(fullSize.getText().toString());
                priceNum = Double.valueOf(price.getText().toString());

                tvpResult = (fullSizeNum*proteinNum/perGrams);
                //tvpResult = (perGrams /100 * proteinNum * fullSizeNum);
                ProteinPerBuckResult = tvpResult /priceNum;

                String tva = String.format("%.2f", tvpResult);
                String apbr = String.format("%.2f", ProteinPerBuckResult);
                //alcPerBuckResult = Float.valueOf(Math.round(alcPerBuckResult));

                TVA.setText(tva);
                if (ProteinPerBuckResult < 100) ProteinPerBuck.setTextColor(getResources().getColor(R.color.Green));
                if (ProteinPerBuckResult < 40) ProteinPerBuck.setTextColor(getResources().getColor(R.color.Yellow));
                if (ProteinPerBuckResult < 8) ProteinPerBuck.setTextColor(getResources().getColor(R.color.Red));
                ProteinPerBuck.setText(apbr);



            }
        });

        buttonGoToMain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(ProteinActivity.this, MainActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                ProteinActivity.this.startActivity(myIntent);



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