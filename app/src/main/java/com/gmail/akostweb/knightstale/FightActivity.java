package com.gmail.akostweb.knightstale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FightActivity extends Activity {

    public static final int FIRST = 1;

    public static final double CRITICAL_HIT = 2.5;
    public static final int MINIMAL_STATS = 8;
    public static final int BONUS_RATE_STATS = 3;
    public static final int DEFAULT_STATS = 2;
    public static final int NO_ANSWER = 1;
    public static final int NO_ANSWER_ZERO = 0;
    public static final int VITALITY_BONUS_HP = 500;
    public static final int STRENGTH_BONUS_HP = 100;
    public static final int STRENGTH_BONUS_DAMAGE = 40;
    public static final int VITALITY_BONUS_DAMAGE = 10;
    public static final int FOCUS_BONUS = 9;
    public static final int AGILITY_BONUS = 6;
    public static final int VITALITY = 1;
    public static final int STRENGTH = 2;
    public static final int AGILITY = 3;
    public static final int FOCUS = 4;
    public static final int DRAW = 0;
    public static final int WINNER_FIRST = 1;
    public static final int WINNER_SECOND = 2;
    public static final boolean CRITICAL = true;
    public static final boolean EVASION = false;
    public static final int PLAYER = 1;
    public static final int BOT1 = 2;
    public static final int BOT2 = 3;


    final public static int ONE = 1;
    final public static int TWO = 2;
    final public static int THREE = 3;
    final public static int FOUR = 4;
    final public static int TEN = 10;

    TextView tvVitality1, tvVitality2, tvStrength1, tvStrength2, tvAgility1, tvAgility2, tvFocus1,
            tvFocus2, tvFightHP1, tvFightHP2, tvFirstName, tvSecondName, tvCritChance1,
            tvCritChance2, tvEvasion1, tvEvasion2, tvDamage1, tvDamage2;

    Button btnFight;

    int hpFirst, hpSecond, hpDamaged, hpLost, exp;

    boolean stopper = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fight_activity);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
        int strength = intent.getIntExtra("strength", NO_ANSWER);
        int vitality = intent.getIntExtra("vitality", NO_ANSWER);
        int agility = intent.getIntExtra("agility", NO_ANSWER);
        int focus = intent.getIntExtra("focus", NO_ANSWER);
        int hp = intent.getIntExtra("hp", NO_ANSWER);
        int typeOfMonster = intent.getIntExtra("lvl", NO_ANSWER);
        int roundCount = intent.getIntExtra("roundCount", NO_ANSWER);
        int bonusStats = intent.getIntExtra("lvl monster", NO_ANSWER_ZERO);
        int id = intent.getIntExtra("id", NO_ANSWER_ZERO);

        final HeroClass player = new HeroClass(name, vitality, strength, agility, focus,
                hp, roundCount, id);
        setStatsHero(player, hp);

        final HeroClass monster = new HeroClass(randomMonsterName(typeOfMonster), DEFAULT_STATS,
                DEFAULT_STATS, DEFAULT_STATS, DEFAULT_STATS, 4);

        setStatsBot(monster, MINIMAL_STATS + typeOfMonster + player.getRoundCount() +
                bonusStats * BONUS_RATE_STATS);

        fillAllTexts(player, monster);



        if (player.getId() == BOT1 || player.getId() == BOT2) {
            hpDamaged = 0;
            hpLost = 0;
            btnFight.setVisibility(View.INVISIBLE);
            MyTaskParams params = new MyTaskParams(player, monster);
            MyTask myTask = new MyTask();
            myTask.execute(params);
        }

        if (player.getId() == PLAYER) {
            btnFight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    roundFight(player, monster);
                }
            });
        }

    } // one method called in it roundFight

    // Logic of the fighting

    public void writingText(final HeroClass heroClass, final TextView tv, final int what) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (what == FIRST) {
                    tvDamage1.setTextColor(Color.RED);
                    tv.setText(getResources().getString(R.string.evasion_critical_strike, String.valueOf(heroClass.getName())));
                } else if (what == TWO) {
                    tvDamage1.setTextColor(Color.RED);
                    tv.setText(getResources().getString(R.string.critical_strike,
                            String.valueOf(heroClass.getName()), String.valueOf(Math.round
                                    (heroClass.getDamage() * CRITICAL_HIT))));
                } else if (what == THREE) {
                    tvDamage1.setTextColor(Color.BLACK);
                    tv.setText(getResources().getString(R.string.evasion,
                            String.valueOf(heroClass.getName())));

                } else if (what == FOUR) {
                    tvDamage1.setTextColor(Color.BLACK);
                    tv.setText(getResources().getString(R.string.hit, String.valueOf(heroClass.getName()), String.valueOf(heroClass.getDamage())));
                }
            }
        });

    }

    public void roundFight(final HeroClass hero, final HeroClass monster) {
        stopper = true;

        if (randomCriticalEvasion(hero, CRITICAL)) {
            if (randomCriticalEvasion(monster, EVASION)) {
                hpSecond = monster.getHp();
                writingText(monster, tvDamage1, FIRST);
            } else {
                hpSecond = (int) Math.round(monster.getHp() - hero.getDamage() * CRITICAL_HIT);
                hpDamaged = (int) (hpDamaged + hero.getDamage()*CRITICAL_HIT);
                writingText(hero, tvDamage1, TWO);
            }

        } else {

            if (randomCriticalEvasion(monster, EVASION)) {
                writingText(monster, tvDamage1, THREE);
                hpSecond = monster.getHp();
            } else {
                hpSecond = monster.getHp() - hero.getDamage();
                hpDamaged = hpDamaged + hero.getDamage();
                writingText(hero, tvDamage1, FOUR);
            }
        }

        if (randomCriticalEvasion(monster, CRITICAL)) {
            if (randomCriticalEvasion(hero, EVASION)) {
                hpFirst = hero.getHp();
                writingText(hero, tvDamage2, FIRST);
            } else {
                hpFirst = (int) Math.round(hero.getHp() - monster.getDamage() * CRITICAL_HIT);
                hpLost = (int) (hpLost + monster.getDamage() * CRITICAL_HIT);
                writingText(monster, tvDamage2, TWO);
            }

        } else {
            if (randomCriticalEvasion(hero, EVASION)) {
                writingText(hero, tvDamage2, THREE);
                hpFirst = hero.getHp();

            } else {
                hpFirst = hero.getHp() - monster.getDamage();
                hpLost = hpLost + monster.getDamage();
                writingText(monster, tvDamage2, FOUR);
            }
        }



        hero.setHp(hpFirst);
        monster.setHp(hpSecond);
        fillHpAfterRound(hero, monster);
        if (hpFirst <= 0 || hpSecond <= 0) stopper = false;
        hpChecker(hero, monster);

    }//main method

    public boolean randomCriticalEvasion(HeroClass hero, boolean critOrEvasion) {

        int min = 0;
        int max = 100;
        int randomNumber;

        Random random = new Random();
        randomNumber = random.nextInt(max - min + 1) + min;
        if (critOrEvasion) {
            return randomNumber <= hero.getCrit();
        } else {
            return randomNumber <= hero.getEvasion();
        }

    } //Critical strike and evasion working

    public void fillHpAfterRound(final HeroClass player, final HeroClass monster) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvFightHP1.setText(getResources().getString(R.string.hp_bar, String.valueOf(player.getHp()),
                        String.valueOf(player.getVitality() * VITALITY_BONUS_HP +
                                player.getStrength() * STRENGTH_BONUS_HP)));
                tvFightHP2.setText(getResources().getString(R.string.hp_bar, String.valueOf(monster.getHp()),
                        String.valueOf(monster.getVitality() * VITALITY_BONUS_HP +
                                monster.getStrength() * STRENGTH_BONUS_HP)));
            }
        });

    } // refresh hp of players

    public void fightResult(final HeroClass player, final HeroClass monster, final int result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnFight.setEnabled(false);
                @SuppressLint("ValidFragment") final
                DialogFragment fightResult = new DialogFragment() {
                    @Override
                    public Dialog onCreateDialog(Bundle savedInstanceState) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        setCancelable(false);
                        if (result == DRAW) {
                            builder.setMessage(getResources().getString(R.string.draw));
                        } else if (result == WINNER_FIRST) {
                            builder.setMessage(getResources().getString(R.string.dead,
                                    String.valueOf(player.getName()),
                                    String.valueOf(monster.getName())));
                        } else if (result == WINNER_SECOND) {
                            builder.setMessage(getResources().getString(R.string.dead,
                                    String.valueOf(monster.getName()),
                                    String.valueOf(player.getName())));
                        }
                        if (player.getId() == PLAYER){
                            builder.setPositiveButton(R.string.back_to_map,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent = new Intent();
                                            intent.putExtra("hp1", String.valueOf(player.getHp()));
                                            intent.putExtra("id1", String.valueOf(player.getId()));
                                            intent.putExtra("name", player.getName());
                                            intent.putExtra("hpDamaged", String.valueOf(hpDamaged));
                                            intent.putExtra("hpLost", String.valueOf(hpLost));
                                            intent.putExtra("exp", String.valueOf(exp));
                                            setResult(RESULT_OK, intent);
                                            finish();

                                        }
                                    });
                        } else {
                            Intent intent = new Intent();
                            intent.putExtra("hp1", String.valueOf(player.getHp()));
                            intent.putExtra("id1", String.valueOf(player.getId()));
                            intent.putExtra("hpDamaged", String.valueOf(hpDamaged));
                            intent.putExtra("hpLost", String.valueOf(hpLost));
                            intent.putExtra("name", player.getName());
                            intent.putExtra("exp", String.valueOf(exp));
                            setResult(RESULT_OK, intent);
                            finish();
                        }


                        return builder.create();
                    }
                };
                fightResult.show(getFragmentManager(), "fight result");

            }
        });

    } // show who is winner

    public void hpChecker(HeroClass hero, HeroClass monster) {
        if (hero.getHp() <= 0 && monster.getHp() <= 0) {
            //btnFight.setEnabled(false);
            fightResult(hero, monster, DRAW);
        } else if (hero.getHp() <= 0) {
            //btnFight.setEnabled(false);
            fightResult(hero, monster, WINNER_FIRST);
        } else if (monster.getHp() <= 0) {
            //btnFight.setEnabled(false);
            fightResult(hero, monster, WINNER_SECOND);
        }
    } // watching hp and called fight result if smdy dead

    //___________________________________________________________________________________

    // Generating clone of player hero

    public void setStatsHero(HeroClass hero, int hp) {
        hero.setDamage(hero.getStrength() * STRENGTH_BONUS_DAMAGE + hero.getVitality() * VITALITY_BONUS_DAMAGE);
        hero.setCrit(hero.getFocus() * FOCUS_BONUS);
        hero.setEvasion(hero.getAgility() * AGILITY_BONUS);
        hero.setHp(hp);
    }

    //____________________________________________________________________________________

    //Random stats bot creator
    public void setStatsBot(HeroClass monster, int numberOfStats) {
        creatorMonster(monster, numberOfStats);
        monster.setHp(monster.vitality * VITALITY_BONUS_HP + monster.strength * STRENGTH_BONUS_HP);
        monster.setDamage(monster.strength * STRENGTH_BONUS_DAMAGE + monster.vitality * VITALITY_BONUS_DAMAGE);
        monster.setCrit(monster.focus * FOCUS_BONUS);
        monster.setEvasion(monster.agility * AGILITY_BONUS);
        monster.setRoundCount(NO_ANSWER_ZERO);
        exp = monster.getStrength() + monster.getVitality() + monster.getAgility() +
                monster.getFocus() + numberOfStats + randomAction(ONE, TEN);
    } //Main class

    public void creatorMonster(HeroClass heroClass, int count) {

        for (int i = 1; i <= count; i++) {

            randomStatToMonster();

            if (randomStatToMonster() == VITALITY) {

                heroClass.setVitality(heroClass.vitality + 1);

            } else if (randomStatToMonster() == STRENGTH) {

                heroClass.setStrength(heroClass.strength + 1);

            } else if (randomStatToMonster() == AGILITY) {

                heroClass.setAgility(heroClass.agility + 1);

            } else if (randomStatToMonster() == FOCUS){

                heroClass.setFocus(heroClass.focus + 1);
            }
        }


    }

    public int randomStatToMonster() {
        int min = 1;
        int max = 4;
        int randomStat;
        Random random = new Random();
        randomStat = random.nextInt(max - min + 1) + min;
        return randomStat;

    } // giving back random stat
    //_____________________________________________________________________________________

    // RANDOM NAME CHOOSER ! ! ! Now its just strong or medium or easy

    public String randomMonsterName(int lvl) {
        if (lvl == 1) return "Easy monster";
        if (lvl == 2) return "Medium monster";
        if (lvl == 3) return "Strong monster";
        return "idk";
    } // it will be soon, monsters must be with different name

    //_____________________________________________________________________________________

    //GRAPHIC FILLING IN
    public void fillAllTexts(HeroClass first, HeroClass second) {

        tvFirstName.setText(first.getName());
        tvSecondName.setText(second.getName());

        tvFightHP1.setText(getResources().getString(R.string.hp_bar, String.valueOf(first.getHp()),
                String.valueOf(first.getVitality() * VITALITY_BONUS_HP + first.getStrength()
                        * STRENGTH_BONUS_HP)));
        tvFightHP2.setText(getResources().getString(R.string.hp_bar, String.valueOf(second.getHp()),
                String.valueOf(second.getVitality() * VITALITY_BONUS_HP + second.getStrength()
                        * STRENGTH_BONUS_HP)));

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

    public int randomAction(int from, int till) {

        Random random = new Random();
        return random.nextInt(till - from + 1) + from;
    }

    //_____________________________________________________________________________________

    //

    private static class MyTaskParams {
        HeroClass hero;
        HeroClass monster;

        MyTaskParams(HeroClass hero, HeroClass monster) {
            this.hero = hero;
            this.monster = monster;
        }
    }

    private class MyTask extends AsyncTask<MyTaskParams, Void, Void> {
        @Override
        protected Void doInBackground(MyTaskParams... params) {
            final HeroClass hero = params[0].hero;
            final HeroClass monster = params[0].monster;

            try {
                TimeUnit.SECONDS.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    do {

                        roundFight(hero, monster);
                    } while (stopper);

                }


            });


            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);


        }
    }
}





