package com.example.viikko10;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import java.io.Console;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    WebView web;
    Button button, showinitialize;
    EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = findViewById(R.id.webView);
        button = findViewById(R.id.button);
        url = findViewById(R.id.url);
        showinitialize = findViewById(R.id.showinitialize);
        showinitialize.setOnClickListener(listener);
        button.setOnClickListener(listener);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        ArrayList<String> listUrl = new ArrayList<>();
        int i = 0;
        int luku = 0;
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {
            String website = url.getText().toString();
            switch (v.getId()) {
                case R.id.button:
                    if (website.matches("index.html") == true) {
                        web.loadUrl("file:///android_asset/index.html");
                        web.evaluateJavascript("javascript:shoutOut()", null);
                    } else {
                        if(luku >= i) {
                            web.loadUrl("http://" + website);
                            listUrl.add(i, website);
                            System.out.println(listUrl.get(i) + i);
                            i++;
                            luku = i;
                        } else {
                            listUrl.removeAll(listUrl);
                            web.loadUrl("http://" + website);
                        }
                    }
                    break;
                case R.id.showinitialize:
                    if (website.matches("index.html") == true) {
                        web.evaluateJavascript("javascript:initialize()", null);
                    } else {
                        if ( i > 0) {
                            try {
                                i--;
                                web.loadUrl("http://" + listUrl.get(i));
                                System.out.println(listUrl.get(i));
                            } catch  (IndexOutOfBoundsException e) {
                                System.out.println("DA FUQ?");
                            }

                        } else {
                            ///TODO
                        }

                    }
                    break;
            }


        }
    };

}