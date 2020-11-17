package com.example.zzxing2;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zzxing2.features.scan_qr.IteoraQrCodeManager;
import com.example.zzxing2.features.scan_qr.IteoraScanQrActivity;

public class IteoraChromeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_scan_qr_code).setOnClickListener((v) -> {
            final Intent scanIntent = new Intent(IteoraChromeActivity.this, IteoraScanQrActivity.class);
            startActivityForResult(scanIntent, IteoraQrCodeManager.SCAN_QR_CODE_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == IteoraQrCodeManager.SCAN_QR_CODE_REQUEST_CODE) {
            IteoraQrCodeManager.getInstance().onActivityResult(data, (url) -> {
                ((TextView) findViewById(R.id.tv_url_decoded_text)).setText(getString(R.string.url_to_be_launched, url));
            });
        }
    }
}