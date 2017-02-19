package com.example.antop.editable_discacl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etOrgPrice, etTaxPrice, etDisPrice;
    private Editable etorg2, ettax2, etdis2;
    private TextView disPrice, taxPrice, orgPrice, clear;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        disPrice = (TextView) findViewById(R.id.etdisprice);
        taxPrice = (TextView) findViewById(R.id.ettextprice);
        orgPrice = (TextView) findViewById(R.id.etfinlprice);
        etOrgPrice = (EditText) findViewById(R.id.etorgprice);
        etTaxPrice = (EditText) findViewById(R.id.ettex);
        etDisPrice = (EditText) findViewById(R.id.etdis);
        clear = (TextView) findViewById(R.id.clear);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etOrgPrice.setText("");
                etOrgPrice.requestFocus();
                etOrgPrice.setFocusableInTouchMode(true);
                etDisPrice.setText("");
                etTaxPrice.setText("");

            }
        });


        TextWatcher textWatcher = new TextWatcher() {
            public void afterTextChanged(Editable s) {

                calculateResult();

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        };

        etDisPrice.addTextChangedListener(textWatcher);
        etTaxPrice.addTextChangedListener(textWatcher);
        etOrgPrice.addTextChangedListener(textWatcher);

    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    private void calculateResult() {
        etorg2 = etOrgPrice.getText();
        ettax2 = etTaxPrice.getText();
        etdis2 = etDisPrice.getText();

        if (etorg2.length() > 0 && ettax2.length() > 0 && etdis2.length() <= 0) {

            double number, percentage;
            try {
                number = Double.parseDouble(String.valueOf(etorg2));
                percentage = Double.parseDouble(String.valueOf(ettax2));

                if (percentage >= 0) {
                    double texprice = (percentage / 100.0) * number;
                    double finalprice = number + texprice;
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    float twoDigitsF = Float.valueOf(decimalFormat.format(texprice));
                    taxPrice.setText(String.valueOf(twoDigitsF));
                    DecimalFormat decimalFormat2 = new DecimalFormat("#.##");
                    float twoDigitsF2 = Float.valueOf(decimalFormat2.format(finalprice));
                    orgPrice.setText(String.valueOf(twoDigitsF2));
                    disPrice.setText("0.00");
                }
            } catch (NumberFormatException e) {

                number = 0;
                percentage = 0;

            }

        } else if (etorg2.length() > 0 && etdis2.length() > 0 && ettax2.length() <= 0) {
            double number, percentage;
            try {

                number = Double.parseDouble(String.valueOf(etorg2));
                percentage = Double.parseDouble(String.valueOf(etdis2));

                if (percentage >= 0 && percentage <= 100) {
                    double disprice = (percentage / 100.0) * number;
                    double finalprice = number - disprice;

                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    float twoDigitsF = Float.valueOf(decimalFormat.format(disprice));
                    disPrice.setText(String.valueOf(twoDigitsF));

                    DecimalFormat decimalFormat2 = new DecimalFormat("#.##");
                    float twoDigitsF2 = Float.valueOf(decimalFormat2.format(finalprice));
                    orgPrice.setText(String.valueOf(twoDigitsF2));
                    taxPrice.setText("0.00");
                }
            } catch (NumberFormatException e) {

                number = 0;
                percentage = 0;

            }
        } else if (etorg2.length() > 0 && ettax2.length() > 0 && etdis2.length() >= 0 && etdis2.length() <= 100) {

            double number, percentage, percendis;
            try {

                number = Double.parseDouble(String.valueOf(etorg2));
                percentage = Double.parseDouble(String.valueOf(ettax2));
                percendis = Double.parseDouble(String.valueOf(etdis2));

                if (percendis >= 0 && percendis <= 100) {
                    double texprice = (percentage / 100.0) * number;
                    double finalprice = number + texprice;

                    double disprice = (percendis / 100.0) * finalprice;
                    double texfinalprice = finalprice - disprice;

                    DecimalFormat decimalFormat1 = new DecimalFormat("#.##");
                    float twoDigitsdis = Float.valueOf(decimalFormat1.format(disprice));
                    disPrice.setText(String.valueOf(twoDigitsdis));

                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    float twoDigitsF = Float.valueOf(decimalFormat.format(texprice));
                    taxPrice.setText(String.valueOf(twoDigitsF));

                    DecimalFormat decimalFormat2 = new DecimalFormat("#.##");
                    float twoDigitsF3 = Float.valueOf(decimalFormat2.format(texfinalprice));
                    orgPrice.setText(String.valueOf(twoDigitsF3));

                }
            } catch (NumberFormatException e) {
                number = 0;
                percentage = 0;
                percendis = 0;
            }
        } else if (etorg2.length() > 0 && ettax2.length() <= 0 && etdis2.length() <= 0) {

            double number, percentage, percendis;

            try {
                number = Double.parseDouble(String.valueOf(etorg2));
                percentage = Double.parseDouble("0.00");
                percendis = Double.parseDouble("0.00");

                DecimalFormat decimalFormat2 = new DecimalFormat("#.##");
                float twoDigitsF3 = Float.valueOf(decimalFormat2.format(number));
                //strYouFinal = String.valueOf(twoDigitsF3);
                orgPrice.setText(String.valueOf(twoDigitsF3));
                taxPrice.setText("0.00");
                disPrice.setText("0.00");
            } catch (NumberFormatException e) {
                number = 0;
            }
        } else if (etorg2.length() <= 0) {
            double number = Double.parseDouble("0.00");
            double percentage = Double.parseDouble("0.00");
            double percendis = Double.parseDouble("0.00");

            DecimalFormat decimalFormat2 = new DecimalFormat("#.##");
            float twoDigitsF3 = Float.valueOf(decimalFormat2.format(number));
            //strYouFinal = String.valueOf(twoDigitsF3);
            //orgPrice.setText(strYouFinal);
            orgPrice.setText("0.00");
            taxPrice.setText("0.00");
            disPrice.setText("0.00");

        }
    }

    @Override
    public void onClick(View v) {
    }

}
