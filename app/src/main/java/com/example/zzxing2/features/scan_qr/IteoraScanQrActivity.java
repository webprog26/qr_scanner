package com.example.zzxing2.features.scan_qr;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.zzxing2.R;
import com.google.zxing.client.android.Intents;

public class IteoraScanQrActivity extends AppCompatActivity implements IteoraBarcodeView.OnLaunchButtonClickListener {

    private IteoraCaptureManager capture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_scanner);

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            actionBar.setElevation(0);
        }

        final IteoraBarcodeView iteoraBarcodeView = (IteoraBarcodeView) findViewById(R.id.it_bar_code_view) ;
        iteoraBarcodeView.setOnLaunchButtonClickListener(this);
        final Intent initScannerIntent = new Intent();
        initScannerIntent.putExtra(Intents.Scan.CAMERA_ID, 0);
        initScannerIntent.putExtra(Intents.Scan.SCAN_TYPE, 0);

        capture = new IteoraCaptureManager(this, iteoraBarcodeView.getBarcodeView(), decodedText -> {
            Log.i("qr_scan_deb", decodedText);
            iteoraBarcodeView.onBarCodeResult(decodedText);
        });
        capture.initializeFromIntent(initScannerIntent, savedInstanceState);
        capture.decode();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onResume() {
        super.onResume();
        this.capture.onResume();
    }

    protected void onPause() {
        super.onPause();
        this.capture.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.capture.onDestroy();
    }

    @Override
    public void onLaunchButtonClick(String url) {
        final Intent resultIntent = new Intent();
        resultIntent.putExtra(IteoraQrCodeManager.EXTRA_URL_TOBE_LAUNCHED, url);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}