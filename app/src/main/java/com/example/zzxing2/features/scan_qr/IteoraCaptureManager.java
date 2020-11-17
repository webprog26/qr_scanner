package com.example.zzxing2.features.scan_qr;

import android.app.Activity;
import android.os.Handler;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.List;

class IteoraCaptureManager extends CaptureManager {

    private final DecoratedBarcodeView barcodeView;
    private final Handler handler;

    interface OnBarcodeResultListener {
        void onBarCodeResult(String decodedText);
    }

    private final OnBarcodeResultListener listener;

    public IteoraCaptureManager(Activity activity, DecoratedBarcodeView barcodeView, OnBarcodeResultListener listener) {
        super(activity, barcodeView);
        this.barcodeView = barcodeView;
        this.listener = listener;
        handler = new Handler();
        barcodeView.setStatusText(null);
    }

    private BarcodeCallback barcodeCallback = new BarcodeCallback() {
        @Override
        public void barcodeResult(final BarcodeResult result) {
            barcodeView.pause();
            handler.post(() -> returnResult(result));
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    @Override
    public void decode() {
        barcodeView.decodeSingle(barcodeCallback);
    }

    @Override
    protected void returnResult(BarcodeResult rawResult) {
        if (listener != null) {
            listener.onBarCodeResult(rawResult.getText());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
