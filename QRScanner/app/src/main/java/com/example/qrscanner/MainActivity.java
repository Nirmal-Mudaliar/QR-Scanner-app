package com.example.qrscanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_scan;
    String str_content;
    public static TextView tv_content;
    CardView cv_copytext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialization();                                           // Initializing everything
        
        if (savedInstanceState != null) {                           // handling rotation glitch
            str_content = savedInstanceState.getString("x");
            tv_content.setText(str_content);
        }
        OnScan();  
        
        OnCopyText();
    }

    private void OnCopyText() {
        cv_copytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("txt", tv_content.getText().toString());
                clipboardManager.setPrimaryClip(clip);

            }
        });
    }

    private void OnScan() {
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(getApplicationContext(), ScannerView.class);
                startActivity(itn);
            }
        });
    }

    private void Initialization() {
        btn_scan = findViewById(R.id.btn_scan);
        tv_content = findViewById(R.id.tv_content);
        cv_copytext = findViewById(R.id.id_cv_copytext);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("x", tv_content.getText().toString());
    }

//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//
//    }
}