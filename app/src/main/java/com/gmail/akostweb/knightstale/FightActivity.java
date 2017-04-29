package com.gmail.akostweb.knightstale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.DialogFragment;
import android.renderscript.Sampler;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Администратор on 10.03.2017.
 */

public class FightActivity extends Activity {

    public static final double CRITICAL_HIT = 2.5;

    TextView tvVitality1, tvVitality2, tvStrength1, tvStrength2, tvAgility1, tvAgility2, tvFocus1,
            tvFocus2, tvFightHP1, tvFightHP2, tvFirstName, tvSecondName, tvCritChance1,
            tvCritChance2, tvEvasion1, tvEvasion2, tvDamage1, tvDamage2;

    Button btnFight;

    int hpFirst, hpSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_activity);

        tvFightHP1 = (TextView) findViewById(R.id.tvFightHp1);
        tvFightHP2 = (TextView) findViewById(R.id.tvFightHp2);
        tvFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvSecondName = (TextView) findViewById(R.id.tvSecondName);

        tvVitality1 = (TextView) findViewById(R.id.tvVitality1);
        tvVitality2 = (TextView) findViewById(R.id.tvVitality2);

        tvStrength1 = (TextView) findViewById(R.id.tvStrength1);
        tvStrength2 = (TextView) findViewById(R.id.tvStrength2);

        tvAgility1 = (TextView) findViewById(R.id.tvAgility1);
        tvAgility2 = (TextView) findViewById(R.id.tvAgility2);

        tvFocus1 = (TextView) findViewById(R.id.tvFocus1);
        tvFocus2 = (TextView) findViewById(R.id.tvFocus2);

        tvEvasion1 = (TextView) findViewById(R.id.tvEvasion1);
        tvEvasion2 = (TextView) findViewById(R.id.tvEvasion2);

        tvCritChance1 = (TextView) findViewById(R.id.tvCritChance1);
        tvCritChance2 = (TextView) findViewById(R.id.tvCritChance2);

        tvDamage1 = (TextView) findViewById(R.id.tvDamage1);
        tvDamage2 = (TextView) findViewById(R.id.tvDamage2);

        btnFight = (Button) findViewById(R.id.btnFight);


        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        int strength = intent.getIntExtra("strength", 1);
        int vitality = intent.getIntExtra("vitality", 1);
        int agility = intent.getIntExtra("agility", 1);
        int focus = intent.getIntExtra("focus", 1);
        int hp = intent.getIntExtra("hp", 1);
        int lvl = intent.getIntExtra("lvl", 1);
        int roundCount = intent.getIntExtra("roundCount", 1);
        int option = intent.getIntExtra("lvl monster", 0);

        final HeroClass player = new HeroClass(name, vitality, strength, agility, focus,
                hp, roundCount);
        player.setHp(hp);
        setStatsHero(player);

        final HeroClass monster = new HeroClass(monsterLvlName(lvl), 2, 2, 2, 2);
        creatorMonster(monster, 8 + lvl + player.getRoundCount() + option*3);
        setStatsBot(monster);
        setStatsEverywhere(player, monster);

        btnFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roundFight(player, monster);
                tvFightHP1.setText(getResources().getString(R.string.hp_bar, String.valueOf(player.getHp()),
                        String.valueOf(player.getVitality() * 500 + player.getStrength() * 100)));
                tvFightHP2.setText(getResources().getString(R.string.hp_bar, String.valueOf(monster.getHp()),
                        String.valueOf(monster.getVitality() * 500 + monster.getStrength() * 100)));

                if (hpChecker(monster, player) == 3) {
                    btnFight.setEnabled(true);
                    //Toast.makeText(FightActivity.this, " Next Round", Toast.LENGTH_SHORT).show();

                } else {
                    btnFight.setEnabled(false);
                    @SuppressLint("ValidFragment") final
                    DialogFragment fightResult = new DialogFragment() {
                        @Override
                        public Dialog onCreateDialog(Bundle savedInstanceState) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            setCancelable(false);
                            if (hpChecker(monster, player) == 0) {
                                builder.setMessage(getResources().getString(R.string.draw));
                            } else if (hpChecker(monster, player) == 1) {
                                builder.setMessage(getResources().getString(R.string.dead,
                                        String.valueOf(monster.getName()),
                                        String.valueOf(player.getName())));
                            } else if (hpChecker(monster, player) == 2) {
                                builder.setMessage(getResources().getString(R.string.dead,
                                        String.valueOf(player.getName()),
                                        String.valueOf(monster.getName())));
                            } else {

                            }
                            builder.setPositiveButton(R.string.back_to_map,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent = new Intent();
                                            intent.putExtra("hp1", String.valueOf(player.getHp()));
                                            setResult(RESULT_OK, intent);
                                            finish();

                                        }
                                    });

                            return builder.create();
                        }
                    };

                    fightResult.show(getFragmentManager(), "fight result");
                }


            }
        });

    }

    public String monsterLvlName(int lvl){
        if (lvl == 1) return "Easy monster";
        if (lvl == 2) return "Medium monster";
        if (lvl == 3) return "Strong monster";
        return "idk";
    }

    public int hpChecker(HeroClass hero, HeroClass hero1) {
        if (hero.getHp() <= 0 && hero1.getHp() <= 0) {
            return 0;
        } else if (hero.getHp() <= 0) {
            return 1;
        } else if (hero1.getHp() <= 0) {
            return 2;
        } else {
            return 3;
        }
    }

    public void creatorMonster(HeroClass heroClass, int count) {

        for (int i = 1; i <= count; i++) {
            randomStat();
            if (randomStat() == 1) {
                heroClass.setVitality(heroClass.vitality + 1);
            } else if (randomStat() == 2) {
                heroClass.setStrength(heroClass.strength + 1);
            } else if (randomStat() == 3) {
                heroClass.setAgility(heroClass.agility + 1);
            } else {
                heroClass.setFocus(heroClass.focus + 1);
            }
        }


    }

    public int randomStat() {
        int min = 1;
        int max = 4;
        int randomStat;
        Random random = new Random();
        randomStat = random.nextInt(max - min + 1) + min;
        return randomStat;

    }

    public void roundFight(HeroClass hero, HeroClass hero1) {
        if (randomCriticalEvasion(hero, true)) {
            if (randomCriticalEvasion(hero1, false)) {
                tvDamage1.setTextColor(getResources().getColor(R.color.Red));
                hpSecond = hero1.getHp();
                tvDamage1.setText(getResources().getString(R.string.evasion_critical_strike,
                        String.valueOf(hero1.getName())));
            } else {
                hpSecond = (int) Math.round(hero1.getHp() - hero.getDamage() * CRITICAL_HIT);
                tvDamage1.setTextColor(getResources().getColor(R.color.Red));
                tvDamage1.setText(getResources().getString(R.string.critical_strike,
                        String.valueOf(hero.getName()), String.valueOf(Math.round(hero.getDamage() * CRITICAL_HIT))));
            }

        } else {
            if (randomCriticalEvasion(hero1, false)) {
                tvDamage1.setTextColor(getResources().getColor(R.color.Black));
                tvDamage1.setText(getResources().getString(R.string.evasion,
                        String.valueOf(hero1.getName()), String.valueOf(hero.getName())));
                hpSecond = hero1.getHp();
            } else {
                hpSecond = hero1.getHp() - hero.getDamage();
                tvDamage1.setTextColor(getResources().getColor(R.color.Black));
                tvDamage1.setText(getResources().getString(R.string.hit,
                        String.valueOf(hero.getName()), String.valueOf(hero.getDamage())));
            }
        }


        if (randomCriticalEvasion(hero1, true)) {
            if (randomCriticalEvasion(hero, false)) {
                tvDamage2.setTextColor(getResources().getColor(R.color.Red));
                tvDamage2.setText(getResources().getString(R.string.evasion_critical_strike, String.valueOf(hero.getName())));
                hpFirst = hero.getHp();

            } else {
                hpFirst = (int) Math.round(hero.getHp() - hero1.getDamage() * CRITICAL_HIT);
                tvDamage2.setTextColor(getResources().getColor(R.color.Red));
                tvDamage2.setText(getResources().getString(R.string.critical_strike,
                        String.valueOf(hero1.getName()), String.valueOf(Math.round(hero1.getDamage() * CRITICAL_HIT))));
            }

        } else {
            if (randomCriticalEvasion(hero, false)) {
                tvDamage2.setTextColor(getResources().getColor(R.color.Black));
                tvDamage2.setText(getResources().getString(R.string.evasion,
                        String.valueOf(hero.getName()), String.valueOf(hero1.getName())));
                hpFirst = hero.getHp();

            } else {
                hpFirst = hero.getHp() - hero1.getDamage();
                tvDamage2.setTextColor(getResources().getColor(R.color.Black));
                tvDamage2.setText(getResources().getString(R.string.hit,
                        String.valueOf(hero1.getName()), String.valueOf(hero1.getDamage())));
            }

        }
        hero.setHp(hpFirst);
        hero1.setHp(hpSecond);

    }

    public boolean randomCriticalEvasion(HeroClass hero, boolean critOrEvasion) {

        int min = 0;
        int max = 100;
        int randomNumber;

        Random random = new Random();
        randomNumber = random.nextInt(max - min + 1) + min;
        if (critOrEvasion) {
            if (randomNumber <= hero.getCrit()) {
                return true;
            } else {
                return false;
            }
        } else {
            if (randomNumber <= hero.getEvasion()) {
                return true;
            } else {
                return false;
            }
        }

    }

    public void setStatsEverywhere(HeroClass first, HeroClass second) {

        tvFirstName.setText(first.getName());
        tvSecondName.setText(second.getName());

        tvFightHP1.setText(getResources().getString(R.string.hp_bar, String.valueOf(first.getHp()),
                String.valueOf(first.getVitality() * 500 + first.getStrength() * 100)));
        tvFightHP2.setText(getResources().getString(R.string.hp_bar, String.valueOf(second.getHp()),
                String.valueOf(second.getVitality() * 500 + second.getStrength() * 100)));

        tvVitality1.setText(String.valueOf(first.getVitality()));
        tvVitality2.setText(String.valueOf(second.getVitality()));

        tvStrength1.setText(String.valueOf(first.getStrength()));
        tvStrength2.setText(String.valueOf(second.getStrength()));

        tvAgility1.setText(String.valueOf(first.getAgility()));
        tvAgility2.setText(String.valueOf(second.getAgility()));

        tvFocus1.setText(String.valueOf(first.getFocus()));
        tvFocus2.setText(String.valueOf(second.getFocus()));

        tvEvasion1.setText(String.valueOf(first.getEvasion()));
        tvEvasion2.setText(String.valueOf(second.getEvasion()));

        tvCritChance1.setText(String.valueOf(first.getCrit()));
        tvCritChance2.setText(String.valueOf(second.getCrit()));


    }


    public void setStatsHero(HeroClass hero) {
        //hero.setHp(hero.vitality * 500 + hero.strength * 100);
        hero.setDamage(hero.strength * 40 + hero.vitality * 10);
        hero.setCrit(hero.focus * 9);
        hero.setEvasion(hero.agility * 6);
        //hero.setStep(1);
        //hero.setRoundCount(0);

    }

    public void setStatsBot(HeroClass hero) {
        hero.setHp(hero.vitality * 500 + hero.strength * 100);
        hero.setDamage(hero.strength * 40 + hero.vitality * 10);
        hero.setCrit(hero.focus * 9);
        hero.setEvasion(hero.agility * 6);
        //hero.setStep(1);
        hero.setRoundCount(0);

    }
}
