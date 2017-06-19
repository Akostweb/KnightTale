package com.gmail.akostweb.knightstale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class OutpostFragment extends DialogFragment implements OnClickListener {

    private static final String LOG_TAG = "goga";
    int otvet;



    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.outpost)
                .setPositiveButton(R.string.buy_outpost, this)
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
                otvet = 1;
                ((GameMap)getActivity()).saveOutpostAnswer(1);
                Toast.makeText(getActivity(), "answer " + i, Toast.LENGTH_LONG).show();
                dismiss();
                break;
            case Dialog.BUTTON_NEGATIVE:
                i = R.string.no;
                otvet = 2;
                ((GameMap)getActivity()).saveOutpostAnswer(2);
                Toast.makeText(getActivity(), "answer " + i, Toast.LENGTH_LONG).show();
                dismiss();

                break;
            case Dialog.BUTTON_NEUTRAL:
                i = R.string.maybe;
                otvet = 3;
                ((GameMap)getActivity()).saveOutpostAnswer(3);
                Toast.makeText(getActivity(), "answer " + i, Toast.LENGTH_LONG).show();
                dismiss();
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

    @Override
    public void onDetach() {
        ((GameMap)getActivity()).saveOutpostAnswer(otvet);
        super.onDetach();
    }

    @Override
    public void onAttach(Activity activity) {
        if(otvet == 0 ){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            ((GameMap)getActivity()).saveOutpostAnswer(otvet);
        }

        super.onAttach(activity);
    }

    public void connector(){
        Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
    }
}
