package com.gmail.akostweb.knightstale;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;


public class HouseDialog extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle("title")
                .setIcon(R.drawable.arena)
                .setCancelable(false);
        return null;
    }


}
