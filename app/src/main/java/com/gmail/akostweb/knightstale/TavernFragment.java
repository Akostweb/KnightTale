package com.gmail.akostweb.knightstale;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class TavernFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private static final String LOG_TAG = "goga";


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.tavern)
                .setPositiveButton(R.string.buy_tavern, this)
                .setNegativeButton(R.string.pay_pass, this)
                .setNeutralButton(R.string.break_through, this)
                .setMessage(R.string.location)
                .setCancelable(true);

        return adb.create();
    }

    public void onClick(DialogInterface dialog, int which) {
        int i = 0;
        switch (which) {
            case Dialog.BUTTON_POSITIVE:

                i = R.string.yes;
                ((GameMap)getActivity()).saveTavernAnswer(1);
                break;
            case Dialog.BUTTON_NEGATIVE:
                ((GameMap)getActivity()).saveTavernAnswer(2);
                i = R.string.no;
                break;
            case Dialog.BUTTON_NEUTRAL:
                ((GameMap)getActivity()).saveTavernAnswer(3);
                i = R.string.maybe;
                break;
        }
        if (i > 0)
            Log.d(LOG_TAG, "Dialog 2: " + getResources().getString(i));
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog 2: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 2: onCancel");
    }

    public void connector(){
        Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
    }
}
