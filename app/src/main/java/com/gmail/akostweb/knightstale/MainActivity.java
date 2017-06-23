package com.gmail.akostweb.knightstale;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void OnClick(View v){
        switch (v.getId()){
            case R.id.btnMainStart:
                Intent intentStart = new Intent(MainActivity.this, createHero.class);
                startActivity(intentStart);
                break;
            case R.id.btnOptions:
                Intent intentOptions = new Intent(MainActivity.this, Options.class);
                startActivity(intentOptions);
                break;
            case R.id.btnFaq:
                Intent intentFaq = new Intent(MainActivity.this, Faq.class);
                startActivity(intentFaq);
                break;
            case R.id.btnExit:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }

    }
}
