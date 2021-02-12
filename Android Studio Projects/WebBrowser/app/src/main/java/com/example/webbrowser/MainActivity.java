package com.example.webbrowser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainView = new LinearLayout(this);

        // Create top layout for buttons and address bar
        LinearLayout topLayout = new LinearLayout(this);
        mainView.addView(topLayout);

        // Create back and forward button
        LabledButton backButton = new LabledButton("B", 135, this);
        LabledButton forwardButton = new LabledButton("F", 135, this);

        topLayout.addView(backButton);
        topLayout.addView(forwardButton);

        // Create address bar
        final AppCompatEditText addressBar = new AppCompatEditText(this);
        topLayout.addView(addressBar);
        addressBar.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(675, 150);
        addressBar.setLayoutParams(params);

        // Create go button
        final LabledButton goButton = new LabledButton("G", 135, this);
        topLayout.addView(goButton);

        mainView.setOrientation(LinearLayout.VERTICAL);
        setContentView(mainView);

        // Create web view
        final WebView webView = new WebView(this);
        webView.loadUrl("https://google.com");
        addressBar.setText("https://google.com");
        webView.setWebViewClient(new WebViewClient());

        // Create url node for history
        final LinkedList history = new LinkedList("https://google.com");

        // Address bar functionality
        goButton.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newAddress = addressBar.getText().toString();
                webView.loadUrl(newAddress);

                history.addUrl(newAddress, history.head);
                history.changeCurrToNewest(history.head);
            }
        });

        // Create back and next buttons functionality
        backButton.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (history.curr.last != null) {
                    webView.loadUrl(history.curr.last.url);
                    history.changeCurrToLast();
                    addressBar.setText(history.curr.url);
                }
            }
        });

        forwardButton.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (history.curr.next != null) {
                    webView.loadUrl(history.curr.next.url);
                    history.changeCurrToNext();
                    addressBar.setText(history.curr.url);
                }
            }
        });

        mainView.addView(webView);
    }
}