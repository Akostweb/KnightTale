package com.gmail.akostweb.knightstale;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameMap extends AppCompatActivity implements DialogInterface.OnClickListener {

    final public static String OUTPOST_NAME = "Outpost on a river";
    final public static String TAVERN_NAME = "Taver 'Small pony'";
    final public static String TOWER_NAME = "Eagle tower";
    final public static String FORTRESS_NAME = "Fortress of free people";
    final public static String CASTLE_NAME = "Castle black stone";
    final public static int OWNER_NULL = 0;
    final public static int OWNER_PLAYER = 1;

    final public static int OUTPOST_COST = 20;
    final public static int TAVERN_COST = 27;
    final public static int TOWER_COST = 32;
    final public static int FORTRESS_COST = 42;
    final public static int CASTLE_COST = 50;

    final public static int OUTPOST_PAY_PASS = 3;
    final public static int TAVERN_PAY_PASS = 5;
    final public static int TOWER_PAY_PASS = 7;
    final public static int FORTRESS_PAY_PASS = 10;
    final public static int CASTLE_PAY_PASS = 15;

    final public static int DAMAGE_JAIL = 250;
    final public static int ALL_ATTEMPTS = 3;

    final public static int POTION_COST = 50;

    final public static int PLAYER = 1;
    final public static int BOT1 = 2;
    final public static int BOT2 = 3;

    final public static int STEP_MAX = 16;
    final public static int MONSTER_EASY = 1;
    final public static int MONSTER_MEDIUM = 2;
    final public static int MONSTER_HARD = 3;
    final public static int MONSTER_BONUS_NULL = 0;
    final public static int MONSTER_BONUS_STRENGTH_LOW = 1;
    final public static int MONSTER_BONUS_STRENGTH_MEDIUM = 2;
    final public static int MONSTER_BONUS_STRENGTH_HARDCORE = 3;
    public static final int VITALITY_BONUS_HP = 500;
    public static final int STRENGTH_BONUS_HP = 100;
    public static final int STRENGTH_BONUS_DAMAGE = 40;
    public static final int VITALITY_BONUS_DAMAGE = 10;
    public static final int FOCUS_BONUS = 9;
    public static final int AGILITY_BONUS = 6;
    public static final int ZERO = 0;
    public static final int START_STATS_DEFAULT = 20;
    public static final int STATS_TO_PURCHASE = 10;
    public static final int OUTPOST = 2;
    public static final int TAVERN = 6;
    public static final int TOWER = 10;
    public static final int FORTRESS = 12;
    public static final int CASTLE = 15;

    int attempts;
    boolean stopper, jailChecker;
    int step,stepbot1, stepbot2, timeToWait;

    int ownerOutpost, ownerTavern, ownerTower, ownerFortress, ownerCastle;

    TextView tvTopName, tvHp, tvBotName1, tvHpBot1, tvBotName2, tvHpBot2, tvGoldShow, tvExp, tvGoldShowBot1, tvGoldShowBot2, tvExpBot1, tvExpBot2;
    Button ivDice, ivNext, btnLvlPlusStrength, btnLvlPlusVitality, btnLvlPlusAgility, btnLvlPlusFocus;
    ImageView iv11;
    ImageView iv12;
    ImageView iv13;
    ImageView iv21;
    ImageView iv22;
    ImageView iv23;
    ImageView iv31;
    ImageView iv32;
    ImageView iv33;
    ImageView iv41;
    ImageView iv42;
    ImageView iv43;
    ImageView iv51;
    ImageView iv52;
    ImageView iv53;
    ImageView iv61;
    ImageView iv62;
    ImageView iv63;
    ImageView iv71;
    ImageView iv72;
    ImageView iv73;
    ImageView iv81;
    ImageView iv82;
    ImageView iv83;
    ImageView iv91;
    ImageView iv92;
    ImageView iv93;
    ImageView iv101;
    ImageView iv102;
    ImageView iv103;
    ImageView iv111;
    ImageView iv112;
    ImageView iv113;
    ImageView iv121;
    ImageView iv122;
    ImageView iv123;
    ImageView iv131;
    ImageView iv132;
    ImageView iv133;
    ImageView iv141;
    ImageView iv142;
    ImageView iv143;
    ImageView iv151;
    ImageView iv152;
    ImageView iv153;
    ImageView iv161;
    ImageView iv162;
    ImageView iv163;

    int hpBeforeFight, hpMaxBeforeFight, hpMaxBeforeFightBot1, hpMaxBeforeFightBot2, hpBeforeFightBot1, hpBeforeFightBot2;
    int maxExperience, experience, lvl;

    private String[] nameArray = {"Asshole", "Cock", "Dick", "Tits", "Bobby", "Hump", "Bravo", "super", "black", "gammy"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //container = (FrameLayout) findViewById(R.id.container);

        btnLvlPlusVitality = (Button) findViewById(R.id.btnLvlPlusVit);
        btnLvlPlusStrength = (Button) findViewById(R.id.btnLvlPlusStrength);
        btnLvlPlusAgility = (Button) findViewById(R.id.btnLvlPlusAgility);
        btnLvlPlusFocus = (Button) findViewById(R.id.btnLvlPlusFocus);


        //all step dots

        iv11 = (ImageView) findViewById(R.id.iv11);
        iv12 = (ImageView) findViewById(R.id.iv12);
        iv13 = (ImageView) findViewById(R.id.iv13);
        iv21 = (ImageView) findViewById(R.id.iv21);
        iv22 = (ImageView) findViewById(R.id.iv22);
        iv23 = (ImageView) findViewById(R.id.iv23);
        iv31 = (ImageView) findViewById(R.id.iv31);
        iv32 = (ImageView) findViewById(R.id.iv32);
        iv33 = (ImageView) findViewById(R.id.iv33);
        iv41 = (ImageView) findViewById(R.id.iv41);
        iv42 = (ImageView) findViewById(R.id.iv42);
        iv43 = (ImageView) findViewById(R.id.iv43);
        iv51 = (ImageView) findViewById(R.id.iv51);
        iv52 = (ImageView) findViewById(R.id.iv52);
        iv53 = (ImageView) findViewById(R.id.iv53);
        iv61 = (ImageView) findViewById(R.id.iv61);
        iv62 = (ImageView) findViewById(R.id.iv62);
        iv63 = (ImageView) findViewById(R.id.iv63);
        iv71 = (ImageView) findViewById(R.id.iv71);
        iv72 = (ImageView) findViewById(R.id.iv72);
        iv73 = (ImageView) findViewById(R.id.iv73);
        iv81 = (ImageView) findViewById(R.id.iv81);
        iv82 = (ImageView) findViewById(R.id.iv82);
        iv83 = (ImageView) findViewById(R.id.iv83);
        iv91 = (ImageView) findViewById(R.id.iv91);
        iv92 = (ImageView) findViewById(R.id.iv92);
        iv93 = (ImageView) findViewById(R.id.iv93);
        iv101 = (ImageView) findViewById(R.id.iv101);
        iv102 = (ImageView) findViewById(R.id.iv102);
        iv103 = (ImageView) findViewById(R.id.iv103);
        iv111 = (ImageView) findViewById(R.id.iv111);
        iv112 = (ImageView) findViewById(R.id.iv112);
        iv113 = (ImageView) findViewById(R.id.iv113);
        iv121 = (ImageView) findViewById(R.id.iv121);
        iv122 = (ImageView) findViewById(R.id.iv122);
        iv123 = (ImageView) findViewById(R.id.iv123);
        iv131 = (ImageView) findViewById(R.id.iv131);
        iv132 = (ImageView) findViewById(R.id.iv132);
        iv133 = (ImageView) findViewById(R.id.iv133);
        iv141 = (ImageView) findViewById(R.id.iv141);
        iv142 = (ImageView) findViewById(R.id.iv142);
        iv143 = (ImageView) findViewById(R.id.iv143);
        iv151 = (ImageView) findViewById(R.id.iv151);
        iv152 = (ImageView) findViewById(R.id.iv152);
        iv153 = (ImageView) findViewById(R.id.iv153);
        iv161 = (ImageView) findViewById(R.id.iv161);
        iv162 = (ImageView) findViewById(R.id.iv162);
        iv163 = (ImageView) findViewById(R.id.iv163);

        //for player

        tvTopName = (TextView) findViewById(R.id.tvTopName);
        tvHp = (TextView) findViewById(R.id.tvHP);
        tvGoldShow = (TextView) findViewById(R.id.goldShow);
        tvExp = (TextView) findViewById(R.id.tvExp);

        //bot1
        tvBotName1 = (TextView) findViewById(R.id.tvBotName1);
        tvHpBot1 = (TextView) findViewById(R.id.tvHPBot1);
        tvGoldShowBot1 = (TextView) findViewById(R.id.tvGoldShowBot1);
        tvExpBot1 = (TextView) findViewById(R.id.tvExpBot1);


        tvBotName2 = (TextView) findViewById(R.id.tvBotName2);
        tvHpBot2 = (TextView) findViewById(R.id.tvHPBot2);
        tvGoldShowBot2 = (TextView) findViewById(R.id.tvGoldShowBot2);
        tvExpBot2 = (TextView) findViewById(R.id.tvExpBot2);

        // creating hero
        final HeroClass player = new HeroClass();
        creatorHero(player);
        player.setId(PLAYER);
        //__________________________________________________________________________________________


        //Creating bot1
        final HeroClass bot1 = new HeroClass(randomName(), START_STATS_DEFAULT,
                START_STATS_DEFAULT, START_STATS_DEFAULT, START_STATS_DEFAULT , BOT1);
        creatorBot(bot1, STATS_TO_PURCHASE);
        fillTextBot1(bot1);
        //__________________________________________________________________________________________

        //Creating bot2
        HeroClass bot2 = new HeroClass(randomName(), START_STATS_DEFAULT, START_STATS_DEFAULT,
                START_STATS_DEFAULT, START_STATS_DEFAULT, BOT2);
        creatorBot(bot2, STATS_TO_PURCHASE);
        fillTextBot2(bot2);
        //__________________________________________________________________________________________


        final HouseClass outpost = new HouseClass(OWNER_NULL, OUTPOST_COST, OUTPOST_PAY_PASS, OUTPOST_NAME);
        ownerOutpost = outpost.getOwner();

        final HouseClass tavern = new HouseClass(OWNER_NULL, TAVERN_COST, TAVERN_PAY_PASS, TAVERN_NAME);
        ownerTavern = tavern.getOwner();

        final HouseClass tower = new HouseClass(OWNER_NULL, TOWER_COST, TOWER_PAY_PASS, TOWER_NAME);
        ownerTower = tower.getOwner();

        final HouseClass fortress = new HouseClass(OWNER_NULL, FORTRESS_COST, FORTRESS_PAY_PASS, FORTRESS_NAME);
        ownerFortress = fortress.getOwner();

        final HouseClass castle = new HouseClass(OWNER_NULL, CASTLE_COST, CASTLE_PAY_PASS, CASTLE_NAME);
        ownerCastle = castle.getOwner();

        ivNext = (Button) findViewById(R.id.ivNext);
        ivNext.setVisibility(View.INVISIBLE);

        ivDice = (Button) findViewById(R.id.ivDice);

        ivDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jailChecker) {
                    jailAction(player);
                } else {
                    Handler handler = new Handler();
                    randomAnimation(ivDice);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            ivDice.setVisibility(View.INVISIBLE);
                            ivNext.setVisibility(View.VISIBLE);

                            stepController(player, step, PLAYER);

                            stepAction(player, outpost, tavern, tower, fortress, castle);

                            player.setHp(hpBeforeFight);
                        }
                    }, timeToWait);
                }
            }
        });

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivDice.setVisibility(View.VISIBLE);
                ivNext.setVisibility(View.INVISIBLE);
                randomAnimation(ivDice);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stepController(bot1, stepbot1, BOT1);
                    }
                }, timeToWait);
            }
        });
    }

    //its deleting, check for round and drawing new dot
    public void stepController(HeroClass heroClass, int step, int id) {
        stepDotPlayerDeleteOld(heroClass, step, id);
    } //Main method, called from here

    public void stepDotPlayerDeleteOld(HeroClass heroClass, int step, int id) {
        if (id == PLAYER){
            if (heroClass.getStep() == 1) {
                iv11.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 2) {
                iv21.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 3) {
                iv31.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 4) {
                iv41.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 5) {
                iv51.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 6) {
                iv61.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 7) {
                iv71.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 8) {
                iv81.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 9) {
                iv91.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 10) {
                iv101.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 11) {
                iv111.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 12) {
                iv121.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 13) {
                iv131.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 14) {
                iv141.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 15) {
                iv151.setBackgroundResource(R.drawable.transparent);
            } else {
                iv161.setBackgroundResource(R.drawable.transparent);
            }
        } else if (id == BOT1){
            if (heroClass.getStep() == 1) {
                iv12.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 2) {
                iv22.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 3) {
                iv32.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 4) {
                iv42.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 5) {
                iv52.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 6) {
                iv62.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 7) {
                iv72.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 8) {
                iv82.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 9) {
                iv92.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 10) {
                iv102.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 11) {
                iv112.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 12) {
                iv122.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 13) {
                iv132.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 14) {
                iv142.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 15) {
                iv152.setBackgroundResource(R.drawable.transparent);
            } else {
                iv162.setBackgroundResource(R.drawable.transparent);
            }

        } else if(id == BOT2){
            if (heroClass.getStep() == 1) {
                iv13.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 2) {
                iv23.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 3) {
                iv33.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 4) {
                iv43.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 5) {
                iv53.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 6) {
                iv63.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 7) {
                iv73.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 8) {
                iv83.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 9) {
                iv93.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 10) {
                iv103.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 11) {
                iv113.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 12) {
                iv123.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 13) {
                iv133.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 14) {
                iv143.setBackgroundResource(R.drawable.transparent);
            } else if (heroClass.getStep() == 15) {
                iv153.setBackgroundResource(R.drawable.transparent);
            } else {
                iv163.setBackgroundResource(R.drawable.transparent);
            }

        } else {
            Toast.makeText(this, " ID ne naiden", Toast.LENGTH_LONG).show();
        }



        stepChecker(heroClass, step, id);
    }


    public void stepChecker(HeroClass heroClass, int step, int id) {
        heroClass.setStep(heroClass.getStep() + step);
        if (heroClass.getStep() > STEP_MAX) {
            heroClass.setRoundCount(heroClass.getRoundCount()+1);
            heroClass.setStep(heroClass.getStep() - STEP_MAX);
            if (id == PLAYER){

                heroClass.setHp((hpMaxBeforeFight * 2 / 5) + heroClass.getHp());
                if (heroClass.getHp() > hpMaxBeforeFight) heroClass.setHp(hpMaxBeforeFight);
                tvHp.setText(getResources().getString(R.string.hp_bar,
                        String.valueOf(heroClass.getHp()), String.valueOf(hpMaxBeforeFight)));
                hpBeforeFight = heroClass.getHp();
                tvGoldShow.setText(String.valueOf(heroClass.getGold()));

            } else if ( id == BOT1){

                heroClass.setHp((hpMaxBeforeFightBot1 * 2 / 5) + heroClass.getHp());
                if (heroClass.getHp() > hpMaxBeforeFightBot1) heroClass.setHp(hpMaxBeforeFightBot1);
                tvHpBot1.setText(getResources().getString(R.string.hp_bar,
                        String.valueOf(heroClass.getHp()), String.valueOf(hpMaxBeforeFightBot1)));
                hpBeforeFightBot1 = heroClass.getHp();
                tvGoldShowBot1.setText(String.valueOf(heroClass.getGold()));

            } else if ( id == BOT2){

                heroClass.setHp((hpMaxBeforeFightBot2 * 2 / 5) + heroClass.getHp());
                if (heroClass.getHp() > hpMaxBeforeFightBot2) heroClass.setHp(hpMaxBeforeFightBot2);
                tvHpBot1.setText(getResources().getString(R.string.hp_bar,
                        String.valueOf(heroClass.getHp()), String.valueOf(hpMaxBeforeFightBot2)));
                hpBeforeFightBot2 = heroClass.getHp();
                tvGoldShowBot2.setText(String.valueOf(heroClass.getGold()));
            }


            heroClass.setGold(heroClass.getGold() + 5*heroClass.getRoundCount());

            heroClass.setExp(heroClass.getExp() + 100 * heroClass.getRoundCount());
            expChecker(heroClass);

        }

        stepDotPlayerNew(heroClass);
    }

    public void stepDotPlayerNew(HeroClass heroClass) {

        if (heroClass.getStep() == 1) {
            iv11.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 2) {
            iv21.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 3) {
            iv31.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 4) {
            iv41.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 5) {
            iv51.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 6) {
            iv61.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 7) {
            iv71.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 8) {
            iv81.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 9) {
            iv91.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 10) {
            iv101.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 11) {
            iv111.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 12) {
            iv121.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 13) {
            iv131.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 14) {
            iv141.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 15) {
            iv151.setBackgroundResource(R.drawable.shieldsuperthumb);
        } else if (heroClass.getStep() == 16) {
            iv161.setBackgroundResource(R.drawable.shieldsuperthumb);
        }
    }
    // _____________________________________________________________________________________________

    // FUTURE THINGS EXPIERENCE AND LEVEL UP TO one of your stats
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.btnLvlPlusVit:
                Toast.makeText(GameMap.this, "vit +", Toast.LENGTH_SHORT).show();
                disableButtons(false);
                break;
            case R.id.btnLvlPlusStrength:
                Toast.makeText(GameMap.this, "str +", Toast.LENGTH_SHORT).show();
                disableButtons(false);
                break;
            case R.id.btnLvlPlusAgility:
                Toast.makeText(GameMap.this, "agi +", Toast.LENGTH_SHORT).show();
                disableButtons(false);
                break;
            case R.id.btnLvlPlusFocus:
                Toast.makeText(GameMap.this, "foc +", Toast.LENGTH_SHORT).show();
                disableButtons(false);
                break;
        }
    }

    public void disableButtons(boolean b) {
        btnLvlPlusVitality.setEnabled(b);
        btnLvlPlusStrength.setEnabled(b);
        btnLvlPlusAgility.setEnabled(b);
        btnLvlPlusFocus.setEnabled(b);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    public void expChecker(HeroClass heroClass) {
        if (heroClass.getExp() >= 1000) {
            heroClass.setExp(0);

        }
    }


    public String randomName() {
        int min = 0;
        int max = 9;

        Random random = new Random();
        int randomName = random.nextInt(max - min + 1) + min;
        String real = nameArray[randomName];
        nameArray[randomName] = "Johhny";


        return real;

    }
    //______________________________________________________________________________________________

    //Animation of dice


    public void randomAnimation(final Button btn) {

        final int count = randomDiceCount();

        final Handler handler = new Handler();

        final Runnable updateDice = new Runnable() {
            @Override
            public void run() {
                step = randomKubThreadLast(btn);

            }
        };

        final Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= count; i++) {
                    handler.post(updateDice);
                    try {
                        Thread.sleep(300 - (285 / count) * i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    } // main Thread

    public int randomDiceCount() {
        int min = 10;
        int max = 11;

        Random random = new Random();
        int s = random.nextInt(max - min + 1) + min;
        timeToWait = s * 185;
        return s;

    } // method that gives back number that how many time it will dice

    public int randomKubThreadLast(Button btn) {
        int min = 1;
        int max = 6;
        Random random = new Random();
        int randomDice = random.nextInt(max - min + 1) + min;
        image(randomDice, btn);

        return randomDice;


    } //return number of dice or just change picture


    public void image(int random, Button btn) {
        if (random == 1) {
            btn.setBackgroundResource(R.drawable.aone);
        } else if (random == 2) {
            btn.setBackgroundResource(R.drawable.atwo);
        } else if (random == 3) {
            btn.setBackgroundResource(R.drawable.athree);
        } else if (random == 4) {
            btn.setBackgroundResource(R.drawable.afour);
        } else if (random == 5) {
            btn.setBackgroundResource(R.drawable.afive);
        } else if (random == 6) {
            btn.setBackgroundResource(R.drawable.asix);
        }

    }
    //______________________________________________________________________________________________

    //Creates bots (bot1 or bot2)

    // Creating bots and fit all views
    public void creatorBot(HeroClass bot, int count) {

        for (int i = 1; i <= count; i++) {
            randomStat();
            if (randomStat() == 1) {
                bot.setVitality(bot.vitality + 1);
            } else if (randomStat() == 2) {
                bot.setStrength(bot.strength + 1);
            } else if (randomStat() == 3) {
                bot.setAgility(bot.agility + 1);
            } else {
                bot.setFocus(bot.focus + 1);
            }
        }

        setStatsBot(bot);
        bot.setStep(0);
        bot.setRoundCount(0);

    }

    public void setStatsBot(HeroClass bot) {
        bot.setHp(bot.vitality * VITALITY_BONUS_HP + bot.strength * STRENGTH_BONUS_HP);
        bot.setDamage(bot.strength * STRENGTH_BONUS_DAMAGE + bot.vitality * VITALITY_BONUS_DAMAGE);
        bot.setCrit(bot.focus * FOCUS_BONUS);
        bot.setEvasion(bot.agility * AGILITY_BONUS);
        bot.setStep(1);
        bot.setRoundCount(0);
        bot.setGold(200);
        bot.setExp(0);

        lvl = 1;
        maxExperience = 1000 * lvl;
        experience = 0;
    }

    public void fillTextBot1(HeroClass bot1) {
        tvBotName1.setText(bot1.getName());
        tvHp1.setText(getResources().getString(R.string.hp_bar,
                String.valueOf(bot1.getHp()), String.valueOf(bot1.getHp())));
    }

    public void fillTextBot2(HeroClass bot2) {
        tvBotName2.setText(bot2.getName());
        tvHp2.setText(getResources().getString(R.string.hp_bar,
                String.valueOf(bot2.getHp()), String.valueOf(bot2.getHp())));
    }

    public int randomStat() {
        int min = 1;
        int max = 4;
        int randomStat;
        Random random = new Random();
        randomStat = random.nextInt(max - min + 1) + min;
        return randomStat;


    }
    //______________________________________________________________________________________________


    //______________________________________________________________________________________________



    // Fighting after turn or using houseClass:
    // - Action for step dot(House action or fight action)
    // Fighting action:
    // 1. sendStats to fightActivity and starts FightingActivity
    // 2.
    //
    public void stepAction(HeroClass heroClass, HouseClass outpost, HouseClass tavern,
                           HouseClass tower, HouseClass fortress, HouseClass castle) {
        if (heroClass.getStep() == 1) {
            heroClass.setRoundCount(heroClass.getRoundCount() + 1);
            heroClass.setGold(heroClass.getGold() + 5 * heroClass.getRoundCount());

        } else if (heroClass.getStep() == OUTPOST) {

            houseAction(heroClass, outpost);

        } else if (heroClass.getStep() == 3) {

            senderStats(heroClass, MONSTER_EASY, MONSTER_BONUS_NULL);


        } else if (heroClass.getStep() == 4) {

            magicShop(heroClass);

        } else if (heroClass.getStep() == 5) {

            senderStats(heroClass, MONSTER_MEDIUM, MONSTER_BONUS_NULL);

        } else if (heroClass.getStep() == TAVERN) {

            houseAction(heroClass, tavern);

        } else if (heroClass.getStep() == 7) {

           secretAreaAction(heroClass);

        } else if (heroClass.getStep() == 8) {

            senderStats(heroClass, MONSTER_EASY, MONSTER_BONUS_NULL);

        } else if (heroClass.getStep() == 9) {
            jailAction(heroClass);

        } else if (heroClass.getStep() == TOWER) {

            houseAction(heroClass, tower);

        } else if (heroClass.getStep() == 11) {

            senderStats(heroClass, MONSTER_HARD, MONSTER_BONUS_NULL);

        } else if (heroClass.getStep() == FORTRESS) {

            houseAction(heroClass, fortress);

        } else if (heroClass.getStep() == 13) {
            stopper = false;
            arenaAction(heroClass);

            if (stopper) arenaAction(heroClass);

            if (stopper) arenaAction(heroClass);

            Toast.makeText(this, " bitva budet", Toast.LENGTH_SHORT).show();


        } else if (heroClass.getStep() == 14) {

            senderStats(heroClass, MONSTER_MEDIUM, MONSTER_BONUS_NULL);

        } else if (heroClass.getStep() == CASTLE) {

            houseAction(heroClass, castle);

        } else if (heroClass.getStep() == 16) {

            senderStats(heroClass, MONSTER_HARD, MONSTER_BONUS_NULL);

        }
    } // main method for senderStats and houseAction

    public void senderStats(HeroClass heroClass, int lvl, int arena) {
        Intent intent = new Intent(GameMap.this, FightActivity.class);
        heroClass.setHp(hpBeforeFight);
        intent.putExtra("name", heroClass.getName());
        intent.putExtra("vitality", heroClass.getVitality());
        intent.putExtra("strength", heroClass.getStrength());
        intent.putExtra("agility", heroClass.getAgility());
        intent.putExtra("focus", heroClass.getFocus());
        intent.putExtra("hp", heroClass.getHp());
        intent.putExtra("roundCount", heroClass.getRoundCount());
        intent.putExtra("lvl", lvl);
        intent.putExtra("lvl monster", arena);
        hpBeforeFight = heroClass.getHp();
        hpMaxBeforeFight = heroClass.vitality * VITALITY_BONUS_HP + heroClass.strength * STRENGTH_BONUS_HP;
        startActivityForResult(intent, 1);

    } //send stats to fight activity

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            Toast.makeText(this, "Ale de vozvrat", Toast.LENGTH_SHORT).show();
            return;
        }
        String hp = data.getStringExtra("hp1");
        tvHp.setText(getResources().getString(R.string.hp_bar,
                String.valueOf(hp), String.valueOf(hpMaxBeforeFight)));

        hpBeforeFight = Integer.parseInt(hp);
        if (hpBeforeFight <= ZERO) endGame();
        Toast.makeText(this, hpBeforeFight + " ", Toast.LENGTH_SHORT).show();
    } // take back result of the fight

    // taking on HouseClass (can be fighting too)

    public void buttonBuy(HeroClass heroClass, HouseClass houseClass) {

        if (houseClass.getOwner() == 0) {
            if (heroClass.getGold() >= houseClass.getHouseCost()) {
                heroClass.setGold(heroClass.getGold() - houseClass.getHouseCost());
                ownerOutpost = 1;
                houseClass.setOwner(ownerOutpost);
                Toast.makeText(GameMap.this, " you bought it", Toast.LENGTH_SHORT).show();
                tvGoldShow.setText(String.valueOf(heroClass.getGold()));


            } else {
                Toast.makeText(GameMap.this, "not enough money", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(GameMap.this, "Its already have owner", Toast.LENGTH_SHORT).show();
        }
    } // button for bying house

    public void buttonPayPass(HeroClass heroClass, HouseClass houseClass) {
        if (heroClass.getGold() >= 3) {
            heroClass.setGold(heroClass.getGold() - houseClass.costToPass);
            tvGoldShow.setText(String.valueOf(heroClass.getGold()));
        } else {
            senderStats(heroClass, MONSTER_MEDIUM, MONSTER_BONUS_STRENGTH_LOW);
        }


    } // if house already earned by someone else  u buy paypass

    // SenderStats here

    public void magicShop(final HeroClass heroClass) {
        @SuppressLint("ValidFragment") final
        DialogFragment shop = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.shop_layout, null);
                builder.setView(view);
                builder.setCancelable(true);

                Button btnStrength = (Button) view.findViewById(R.id.btnStrength);
                Button btnVitality = (Button) view.findViewById(R.id.btnVitality);
                Button btnAgility = (Button) view.findViewById(R.id.btnAgility);
                Button btnFocus = (Button) view.findViewById(R.id.btnFocus);

                btnStrength.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        heroClass.setStrength(heroClass.getStrength() + 1);
                        heroClass.setDamage(heroClass.getStrength() * STRENGTH_BONUS_DAMAGE + heroClass.getVitality() * VITALITY_BONUS_DAMAGE);
                        hpMaxBeforeFight = heroClass.getVitality() * VITALITY_BONUS_HP + heroClass.getStrength() * STRENGTH_BONUS_HP;
                        tvHp.setText(getResources().getString(R.string.hp_bar,
                                String.valueOf(heroClass.getHp()), String.valueOf(hpMaxBeforeFight)));
                        Toast.makeText(getActivity(), String.valueOf(heroClass.getStrength()), Toast.LENGTH_LONG).show();
                        heroClass.setGold(heroClass.getGold() - POTION_COST);
                        dismiss();

                    }
                });

                btnVitality.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        heroClass.setVitality(heroClass.getVitality() + 1);
                        heroClass.setDamage(heroClass.getStrength() * STRENGTH_BONUS_DAMAGE + heroClass.getVitality() * VITALITY_BONUS_DAMAGE);
                        hpMaxBeforeFight = heroClass.getVitality() * VITALITY_BONUS_HP + heroClass.getStrength() * STRENGTH_BONUS_HP;
                        tvHp.setText(getResources().getString(R.string.hp_bar,
                                String.valueOf(heroClass.getHp()), String.valueOf(hpMaxBeforeFight)));
                        Toast.makeText(getActivity(), String.valueOf(heroClass.getVitality()), Toast.LENGTH_LONG).show();
                        heroClass.setGold(heroClass.getGold() - POTION_COST);

                        dismiss();
                    }
                });

                btnAgility.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        heroClass.setAgility(heroClass.getAgility() + 1);
                        heroClass.setEvasion(heroClass.agility * AGILITY_BONUS);
                        Toast.makeText(getActivity(), String.valueOf(heroClass.getAgility()), Toast.LENGTH_LONG).show();
                        heroClass.setGold(heroClass.getGold() - POTION_COST);
                        dismiss();
                    }
                });

                btnFocus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        heroClass.setFocus(heroClass.getFocus() + 1);
                        heroClass.setCrit(heroClass.focus * FOCUS_BONUS);
                        Toast.makeText(getActivity(), String.valueOf(heroClass.getFocus()), Toast.LENGTH_LONG).show();
                        heroClass.setGold(heroClass.getGold() - POTION_COST);
                        dismiss();
                    }
                });


                return builder.create();

            }


        };
        shop.show(getFragmentManager(), "fight result");

    }


    public void houseAction(final HeroClass heroClass, final HouseClass houseClass) {
        @SuppressLint("ValidFragment") final
        DialogFragment fightResult = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.outpost_layout, null);
                builder.setView(view);
                builder.setCancelable(true);

                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.mainLayout);
                switch (houseClass.getName()) {
                    case OUTPOST_NAME:
                        linearLayout.setBackgroundResource(R.drawable.outpost);
                        break;
                    case TAVERN_NAME:
                        linearLayout.setBackgroundResource(R.drawable.tavernalert);
                        break;
                    case TOWER_NAME:
                        linearLayout.setBackgroundResource(R.drawable.tower);
                        break;
                    case FORTRESS_NAME:
                        linearLayout.setBackgroundResource(R.drawable.fortress);
                        break;
                    default:
                        linearLayout.setBackgroundResource(R.drawable.castle);
                        break;
                }


                TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
                tvTitle.setText(getResources().getString(R.string.path));

                TextView tvText = (TextView) view.findViewById(R.id.tvText);
                tvText.setText(getResources().getString(R.string.what_house, houseClass.getName()));


                Button buy = (Button) view.findViewById(R.id.buttonBuy);
                final Button payPass = (Button) view.findViewById(R.id.buttonPay);
                final Button breakThrough = (Button) view.findViewById(R.id.buttonBreak);
                buy.setText(getResources().getString(R.string.action_buy, String.valueOf(houseClass.getHouseCost())));

                if (houseClass.getOwner() == OWNER_NULL) {
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            buttonBuy(heroClass, houseClass);
                            houseClass.setOwner(1);
                            dismiss();
                        }
                    });

                }

                if (houseClass.getOwner() == OWNER_PLAYER) {
                    buy.setText(getResources().getString(R.string.at_home, heroClass.getName()));
                    payPass.setVisibility(View.GONE);
                    breakThrough.setVisibility(View.GONE);
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            heroClass.setHp(heroClass.getHp() * 2 / 5 + heroClass.getHp());
                            if (heroClass.getHp() > hpMaxBeforeFight)
                                heroClass.setHp(hpMaxBeforeFight);
                            tvHp.setText(getResources().getString(R.string.hp_bar,
                                    String.valueOf(heroClass.getHp()), String.valueOf(hpMaxBeforeFight)));

                            dismiss();
                        }
                    });

                } else {
                    payPass.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            buttonPayPass(heroClass, houseClass);
                            dismiss();
                        }
                    });

                }


                breakThrough.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        senderStats(heroClass, MONSTER_HARD, MONSTER_BONUS_STRENGTH_LOW);
                        dismiss();
                    }
                });

                return builder.create();

            }


        };
        fightResult.show(getFragmentManager(), "fight result");

    }

    public void arenaAction(final HeroClass heroClass) {

        @SuppressLint("ValidFragment") final
        DialogFragment fightResult = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.arena_layout, null);
                builder.setView(view);
                builder.setIcon(R.drawable.arena);
                builder.setCancelable(true);

                Button goAway = (Button) view.findViewById(R.id.buttonRun);
                Button fightStrong = (Button) view.findViewById(R.id.buttonFightStrong);
                Button fightMedium = (Button) view.findViewById(R.id.buttonfightMedium);

                goAway.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        stopper = true;
                        dismiss();
                    }
                });

                fightMedium.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        senderStats(heroClass, MONSTER_MEDIUM, MONSTER_BONUS_STRENGTH_MEDIUM);
                    }
                });

                fightStrong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        senderStats(heroClass, MONSTER_HARD, MONSTER_BONUS_STRENGTH_HARDCORE);
                    }
                });

                return builder.create();

            }


        };
        fightResult.show(getFragmentManager(), "fight result");
    }

    public void jailAction(final HeroClass heroClass) {

        jailChecker = true;

        @SuppressLint("ValidFragment") final
        DialogFragment jailResult = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.jail_layout, null);
                builder.setView(view);
                builder.setCancelable(true);

                final Button diceButton = (Button) view.findViewById(R.id.btnJailDice);

                diceButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Handler handler = new Handler();
                        randomAnimation(diceButton);
                        handler.postDelayed(new Runnable() {
                            public void run() {

                                if (step == 1) {
                                    diceButton.setBackgroundResource(R.drawable.aone);
                                    if (!jailStaying(heroClass)) {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        dismiss();
                                    }
                                } else if (step == 2) {
                                    diceButton.setBackgroundResource(R.drawable.atwo);
                                    if (!jailStaying(heroClass)) {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        dismiss();
                                    }
                                } else if (step == 3) {
                                    diceButton.setBackgroundResource(R.drawable.athree);
                                    if (!jailStaying(heroClass)) {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        dismiss();
                                    }
                                } else if (step == 4) {
                                    diceButton.setBackgroundResource(R.drawable.afour);
                                    if (!jailStaying(heroClass)) {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        dismiss();
                                    }
                                } else if (step == 5) {
                                    diceButton.setBackgroundResource(R.drawable.afive);
                                    if (!jailStaying(heroClass)) {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        dismiss();
                                    }
                                } else if (step == 6) {
                                    diceButton.setBackgroundResource(R.drawable.asix);
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Toast.makeText(getActivity(), "You are free, luck with you", Toast.LENGTH_LONG).show();
                                    dismiss();
                                    attempts = 0;
                                    jailChecker = false;
                                }

                            }
                        }, timeToWait);


                    }
                });

                return builder.create();

            }

        };
        jailResult.show(getFragmentManager(), "Jail result");

    }

    public boolean jailStaying(HeroClass heroClass) {
        attempts++;
        Toast.makeText(this, "You have " + (ALL_ATTEMPTS - attempts) + " attempts", Toast.LENGTH_SHORT).show();
        if (attempts >= 3) {
            heroClass.setHp(heroClass.getHp() - DAMAGE_JAIL);
            tvHp.setText(getResources().getString(R.string.hp_bar,
                    String.valueOf(heroClass.getHp()), String.valueOf(hpMaxBeforeFight)));

            if (heroClass.getHp() <= 0) {
                endGame();
            }
            Toast.makeText(this, "No luck today, your health goind down", Toast.LENGTH_LONG).show();
            attempts = 0;
            return false;
        }

        return true;
    }

    public void endGame() {
        @SuppressLint("ValidFragment") final
        DialogFragment fightResult = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                setCancelable(false);
                builder.setMessage(R.string.title_over);

                builder.setPositiveButton(R.string.game_over,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameMap.this, MainActivity.class);
                                startActivity(intent);


                            }
                        });

                return builder.create();
            }
        };

        fightResult.show(getFragmentManager(), "fight result");

    } // alert for end game

    public void secretAreaAction(final HeroClass heroClass){
        @SuppressLint("ValidFragment") final
        DialogFragment jailResult = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.secret_layout, null);
                builder.setView(view);
                builder.setCancelable(true);

                final Button btnPlay = (Button) view.findViewById(R.id.btnPlay);
                final Button btnExit = (Button) view.findViewById(R.id.btnExit);

                btnPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        actionChooser(randomAction(), heroClass);
                        dismiss();
                    }
                });

                btnExit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        Toast.makeText(getActivity(), R.string.choice, Toast.LENGTH_LONG).show();
                    }
                });

                return builder.create();

            }

        };
        jailResult.show(getFragmentManager(), "Secret result");

    }

    public int randomAction(){

        int min = 1;
        int max = 6;
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public void actionChooser(int randomNumber, HeroClass heroClass){
        if (randomNumber == 1){
            senderStats(heroClass, MONSTER_EASY, MONSTER_BONUS_NULL);
            Toast.makeText(this, "you open the eyes after some time, and see easy enemy. Fight", Toast.LENGTH_LONG).show();
        } else if (randomNumber == 2){
            senderStats(heroClass, MONSTER_MEDIUM, MONSTER_BONUS_NULL);
            Toast.makeText(this, "you open the eyes after some time and see medium monster", Toast.LENGTH_LONG).show();
        } else if (randomNumber == 3){
            magicShop(heroClass);
            Toast.makeText(this, "That strange but you in magic shop, you can boy some potion", Toast.LENGTH_LONG).show();
        } else if (randomNumber == 4){
            senderStats(heroClass, MONSTER_HARD, MONSTER_BONUS_NULL);
            Toast.makeText(this, "you open the eyes after some time and see STRONG monster", Toast.LENGTH_LONG).show();
        } else if (randomNumber == 5){
            senderStats(heroClass, MONSTER_HARD,MONSTER_BONUS_STRENGTH_HARDCORE);
            Toast.makeText(this, "you open the eyes after some time and see BIGBOSS", Toast.LENGTH_LONG).show();
        } else if (randomNumber == 6) {
            arenaAction(heroClass);
            Toast.makeText(this, " that strange but you stay in middle of arena", Toast.LENGTH_LONG).show();
        }

    }


    //______________________________________________________________________________________________

    // Creating hero
    public void creatorHero(HeroClass heroClass) {

        Intent intent = getIntent();
        int vitality = Integer.parseInt(intent.getStringExtra("vitality"));
        int strength = Integer.parseInt(intent.getStringExtra("strength"));
        int agility = Integer.parseInt(intent.getStringExtra("agility"));
        int focus = Integer.parseInt(intent.getStringExtra("focus"));
        String name = intent.getStringExtra("name");

        heroClass.setVitality(vitality);
        heroClass.setStrength(strength);
        heroClass.setAgility(agility);
        heroClass.setFocus(focus);
        heroClass.setName(name);

        setStats(heroClass);


    } // Main method, gives everything for creation

    public void setStats(HeroClass hero) {
        hero.setHp(hero.vitality * VITALITY_BONUS_HP + hero.strength * STRENGTH_BONUS_HP);
        hero.setDamage(hero.strength * STRENGTH_BONUS_DAMAGE + hero.vitality * VITALITY_BONUS_DAMAGE);
        hero.setCrit(hero.focus * FOCUS_BONUS);
        hero.setEvasion(hero.agility * AGILITY_BONUS);
        hero.setStep(1);
        hero.setRoundCount(0);
        hero.setRoundCount(0);
        hero.setGold(200);
        hero.setExp(0);

        hpBeforeFight = hero.getHp();
        hpMaxBeforeFight = hero.getHp();
        lvl = 1;
        maxExperience = 1000 * lvl;
        experience = 0;
        fillText(hero);
    }

    public void fillText(HeroClass heroClass) {
        iv11.setBackgroundResource(R.drawable.shieldsuperthumb);
        tvGoldShow.setText(String.valueOf(heroClass.getGold()));
        tvTopName.setText(heroClass.getName());
        tvHp.setText(getResources().getString(R.string.hp_bar,
                String.valueOf(heroClass.getHp()), String.valueOf(heroClass.getHp())));
        experience();
    }

    public void experience() {
        tvExp.setText(getResources().getString(R.string.experience, String.valueOf(experience),
                String.valueOf(maxExperience)));

    } // can be called from other methods

    //______________________________________________________________________________________________

    // Creating bots to play with

}
