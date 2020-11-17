package com.example.zzxing2.features.scan_qr;

import android.content.Intent;
import android.text.TextUtils;

public class IteoraQrCodeManager {

    public interface OnUrlToBeLaunchedListener {
        void onUrlTobeLaunched(final String url);
    }

    public static final int SCAN_QR_CODE_REQUEST_CODE = 9999;
    public static final String EXTRA_URL_TOBE_LAUNCHED = "extra_url_to_be_launched";

    private static IteoraQrCodeManager instance;

    private IteoraQrCodeManager() {
    }

    public static IteoraQrCodeManager getInstance() {
        if (instance == null) {
            instance = new IteoraQrCodeManager();
        }
        return instance;
    }

    public void onActivityResult(Intent data, OnUrlToBeLaunchedListener listener) {
        if (data != null) {
            final String urlToBeLaunched = data.getStringExtra(EXTRA_URL_TOBE_LAUNCHED);
            if (!TextUtils.isEmpty(urlToBeLaunched)) {
                if (listener != null) {
                    listener.onUrlTobeLaunched(urlToBeLaunched);
                }
            }
        }

    }
}
