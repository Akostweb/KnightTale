package com.gmail.akostweb.knightstale;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;


public class Options extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }
}
