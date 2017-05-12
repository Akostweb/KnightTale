package com.gmail.akostweb.knightstale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.DialogFragment;
import android.renderscript.Sampler;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FightActivity extends Activity {

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
    public static final boolean CRIT = true;
    public static final boolean EVASION = false;

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
        int strength = intent.getIntExtra("strength", NO_ANSWER);
        int vitality = intent.getIntExtra("vitality", NO_ANSWER);
        int agility = intent.getIntExtra("agility", NO_ANSWER);
        int focus = intent.getIntExtra("focus", NO_ANSWER);
        int hp = intent.getIntExtra("hp", NO_ANSWER);
        int typeOfMonster = intent.getIntExtra("lvl", NO_ANSWER);
        int roundCount = intent.getIntExtra("roundCount", NO_ANSWER);
        int bonusStats = intent.getIntExtra("lvl monster", NO_ANSWER_ZERO);

        final HeroClass player = new HeroClass(name, vitality, strength, agility, focus,
                hp, roundCount);
        setStatsHero(player, hp);

        final HeroClass monster = new HeroClass(randomMonsterName(typeOfMonster), DEFAULT_STATS,
                DEFAULT_STATS, DEFAULT_STATS, DEFAULT_STATS);

        setStatsBot(monster, MINIMAL_STATS + typeOfMonster + player.getRoundCount() +
                bonusStats * BONUS_RATE_STATS);

        fillAllTexts(player, monster);

        btnFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roundFight(player, monster);

            }
        });

    } // one method called in it roundFight

    // Logic of the fighting

    public void roundFight(HeroClass hero, HeroClass hero1) {
        if (randomCriticalEvasion(hero, CRIT)) {
            tvDamage1.setTextColor(Color.RED);
            if (randomCriticalEvasion(hero1, EVASION)) {
                hpSecond = hero1.getHp();
                tvDamage1.setText(getResources().getString(R.string.evasion_critical_strike,
                        String.valueOf(hero1.getName())));
            } else {
                hpSecond = (int) Math.round(hero1.getHp() - hero.getDamage() * CRITICAL_HIT);
                tvDamage1.setText(getResources().getString(R.string.critical_strike,
                        String.valueOf(hero.getName()), String.valueOf(Math.round(hero.getDamage() * CRITICAL_HIT))));
            }

        } else {
            tvDamage1.setTextColor(Color.BLACK);
            if (randomCriticalEvasion(hero1, EVASION)) {
                tvDamage1.setText(getResources().getString(R.string.evasion,
                        String.valueOf(hero1.getName()), String.valueOf(hero.getName())));
                hpSecond = hero1.getHp();
            } else {
                hpSecond = hero1.getHp() - hero.getDamage();
                tvDamage1.setText(getResources().getString(R.string.hit,
                        String.valueOf(hero.getName()), String.valueOf(hero.getDamage())));
            }
        }

        if (randomCriticalEvasion(hero1, CRIT)) {
            tvDamage2.setTextColor(Color.RED);
            if (randomCriticalEvasion(hero, EVASION)) {
                tvDamage2.setText(getResources().getString(R.string.evasion_critical_strike, String.valueOf(hero.getName())));
                hpFirst = hero.getHp();
            } else {
                hpFirst = (int) Math.round(hero.getHp() - hero1.getDamage() * CRITICAL_HIT);
                tvDamage2.setText(getResources().getString(R.string.critical_strike,
                        String.valueOf(hero1.getName()), String.valueOf(Math.round(hero1.getDamage() * CRITICAL_HIT))));
            }

        } else {
            tvDamage2.setTextColor(Color.BLACK);
            if (randomCriticalEvasion(hero, EVASION)) {
                tvDamage2.setText(getResources().getString(R.string.evasion,
                        String.valueOf(hero.getName()), String.valueOf(hero1.getName())));
                hpFirst = hero.getHp();

            } else {
                hpFirst = hero.getHp() - hero1.getDamage();
                tvDamage2.setText(getResources().getString(R.string.hit,
                        String.valueOf(hero1.getName()), String.valueOf(hero1.getDamage())));
            }

        }
        hero.setHp(hpFirst);
        hero1.setHp(hpSecond);
        fillHpAfterRound(hero, hero1);
        hpChecker(hero, hero1);


    } //main method

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

    } //Critical strike and evasion working

    public void fillHpAfterRound(HeroClass player, HeroClass monster){
        tvFightHP1.setText(getResources().getString(R.string.hp_bar, String.valueOf(player.getHp()),
                String.valueOf(player.getVitality() * VITALITY_BONUS_HP +
                        player.getStrength() * STRENGTH_BONUS_HP)));
        tvFightHP2.setText(getResources().getString(R.string.hp_bar, String.valueOf(monster.getHp()),
                String.valueOf(monster.getVitality() * VITALITY_BONUS_HP +
                        monster.getStrength() * STRENGTH_BONUS_HP)));
    } // refresh hp of players

    public void fightResult (final HeroClass player , final HeroClass monster, final int result){
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

    } // show who is winner

    public void hpChecker(HeroClass hero, HeroClass hero1) {
        if (hero.getHp() <= 0 && hero1.getHp() <= 0) {
            btnFight.setEnabled(false);
            fightResult(hero, hero1, DRAW);
        } else if (hero.getHp() <= 0) {
            btnFight.setEnabled(false);
            fightResult(hero, hero1, WINNER_FIRST);
        } else if (hero1.getHp() <= 0) {
            btnFight.setEnabled(false);
            fightResult(hero, hero1, WINNER_SECOND);
        } else {
            btnFight.setEnabled(true);
        }
    } // watching hp and called fight result if smdy dead

    //___________________________________________________________________________________

    // Generating clone of player hero

    public void setStatsHero(HeroClass hero, int hp) {
        hero.setDamage(hero.strength * STRENGTH_BONUS_DAMAGE + hero.vitality * VITALITY_BONUS_DAMAGE);
        hero.setCrit(hero.focus * FOCUS_BONUS);
        hero.setEvasion(hero.agility * AGILITY_BONUS);
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

            } else {

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

    //_____________________________________________________________________________________

    //





}
