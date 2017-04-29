package com.gmail.akostweb.knightstale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class createHero extends Activity {

    Button strengthPlus, strengthMinus, vitalityPlus, vitalityMinus,
            agilityPlus, agilityMinus, focusPlus, focusMinus, btnStart;

    TextView etVitality, etStrength, etAgility, etFocus, tvStatsRemain;
    EditText etName;

    public int statsAhead;

    CheckBox cbFirstComp, cbSecondComp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createhero_activity);

        etName = (EditText) findViewById(R.id.etFirstName);

        tvStatsRemain = (TextView) findViewById(R.id.tvStatsRemain);
        etVitality = (TextView) findViewById(R.id.etVitality);
        etStrength = (TextView) findViewById(R.id.etStrength);
        etAgility = (TextView) findViewById(R.id.etAgility);
        etFocus = (TextView) findViewById(R.id.etFocus);

        strengthPlus = (Button) findViewById(R.id.btnPlusStrength);
        strengthMinus = (Button) findViewById(R.id.btnMinusStrength);
        vitalityPlus = (Button) findViewById(R.id.btnPlusVitality);
        vitalityMinus = (Button) findViewById(R.id.btnMinusVitality);
        agilityPlus = (Button) findViewById(R.id.btnPlusAgility);
        agilityMinus = (Button) findViewById(R.id.btnMinusAgility);
        focusPlus = (Button) findViewById(R.id.btnPlusFocus);
        focusMinus = (Button) findViewById(R.id.btnMinusFocus);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setEnabled(true);

        cbFirstComp = (CheckBox) findViewById(R.id.cbFirstComp);
        cbSecondComp = (CheckBox) findViewById(R.id.cbSecondComp);
    }

    public createHero() {
    }

    public void OnClick(View v) {
        switch (v.getId()) {

            case R.id.btnPlusStrength:
                onClickHelper(tvStatsRemain, etStrength, true);
                break;
            case R.id.btnMinusStrength:
                onClickHelper(tvStatsRemain, etStrength, false);
                break;
            case R.id.btnPlusAgility:
                onClickHelper(tvStatsRemain, etAgility, true);
                break;
            case R.id.btnMinusAgility:
                onClickHelper(tvStatsRemain, etAgility, false);
                break;
            case R.id.btnPlusFocus:
                onClickHelper(tvStatsRemain, etFocus, true);
                break;
            case R.id.btnMinusFocus:
                onClickHelper(tvStatsRemain, etFocus, false);
                break;
            case R.id.btnPlusVitality:
                onClickHelper(tvStatsRemain, etVitality, true);
                break;
            case R.id.btnMinusVitality:
                onClickHelper(tvStatsRemain, etVitality, false);
                break;
            case R.id.cbFirstComp:
                statsLeftChecker(tvStatsRemain);
                break;
            case R.id.cbSecondComp:
                statsLeftChecker(tvStatsRemain);
                break;
            case R.id.btnStart:
                Intent intent = new Intent(this, GameMap.class);
                intent.putExtra("vitality", etVitality.getText().toString());
                intent.putExtra("strength", etStrength.getText().toString());
                intent.putExtra("agility", etAgility.getText().toString());
                intent.putExtra("focus", etFocus.getText().toString());
                intent.putExtra("name", etName.getText().toString());
                startActivity(intent);
                break;

        }

    }

    public void onClickHelper(TextView stats, TextView heroStats, boolean plusOrMinus) {
        statsAhead = Integer.parseInt(stats.getText().toString());
        if (plusOrMinus) {
            if (statsAhead > 0) {
                statsAhead--;
                heroStats.setText(String.valueOf(Integer.parseInt(heroStats.getText().toString()) + 1));
                stats.setText(String.valueOf(statsAhead));
            } else {
                Toast.makeText(this, R.string.no_free_stats, Toast.LENGTH_SHORT).show();
            }
        } else {
            if (statsAhead < 10 && Integer.parseInt(heroStats.getText().toString()) > 2) {
                statsAhead++;
                heroStats.setText(String.valueOf(Integer.parseInt(heroStats.getText().toString()) - 1));
                stats.setText(String.valueOf(statsAhead));
            } else {
                Toast.makeText(this, R.string.stats_limit, Toast.LENGTH_SHORT).show();

            }

        }
        statsLeftChecker(tvStatsRemain);


    }

    public void statsLeftChecker(TextView view1) {
        btnStart.setEnabled(true);

//        if (Integer.parseInt(view1.getText().toString()) == 0 && (cbFirstComp.isChecked() || cbSecondComp.isChecked())) {
//            if (etName.length() > 0){
//                btnStart.setEnabled(true);
//
//                vitalityPlus.setEnabled(false);
//                vitalityMinus.setEnabled(false);
//
//                strengthPlus.setEnabled(false);
//                strengthMinus.setEnabled(false);
//
//                agilityPlus.setEnabled(false);
//                agilityMinus.setEnabled(false);
//
//                focusPlus.setEnabled(false);
//                focusMinus.setEnabled(false);
//            } else {
//                Toast.makeText(this, R.string.name_toast, Toast.LENGTH_SHORT).show();
//            }
//
//        } else {
//            btnStart.setEnabled(false);
//        }


    }

}
