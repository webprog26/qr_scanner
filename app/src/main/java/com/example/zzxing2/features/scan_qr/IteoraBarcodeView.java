package com.example.zzxing2.features.scan_qr;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zzxing2.R;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

class IteoraBarcodeView extends RelativeLayout implements IteoraCaptureManager.OnBarcodeResultListener {

    private DecoratedBarcodeView barcodeView;
    private LinearLayout controlsLayout;
    private TextView tvUrlDecoded;
    private Button btnLaunchUrlDecoded;

    private OnLaunchButtonClickListener launchButtonClickListener;

    interface OnLaunchButtonClickListener {
        void onLaunchButtonClick(final String url);
    }

    IteoraBarcodeView(Context context) {
        super(context);
        init(context);
    }

    public IteoraBarcodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_iteora_bar_code, this);
        this.barcodeView = (DecoratedBarcodeView) findViewById(R.id.bar_code_view);
        this.controlsLayout = (LinearLayout) findViewById(R.id.controls);
        this.tvUrlDecoded = (TextView) findViewById(R.id.tv_url_decoded_text);
        this.btnLaunchUrlDecoded = (Button) findViewById(R.id.btn_launch_url_decoded);
        Log.i("qr_scan_deb", "barcodeView: " + barcodeView);
    }

    DecoratedBarcodeView getBarcodeView() {
        return barcodeView;
    }

    void setOnLaunchButtonClickListener(final OnLaunchButtonClickListener listener) {
        this.launchButtonClickListener = listener;
    }

    @Override
    public void onBarCodeResult(String decodedText) {
        controlsLayout.setVisibility(VISIBLE);
        tvUrlDecoded.setText(decodedText);
        btnLaunchUrlDecoded.setOnClickListener((v) -> {
            if (launchButtonClickListener != null) {
                launchButtonClickListener.onLaunchButtonClick(decodedText);
            }
        });
    }
}
