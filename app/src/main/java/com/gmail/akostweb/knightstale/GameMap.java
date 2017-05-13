package com.gmail.akostweb.knightstale;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameMap extends AppCompatActivity implements DialogInterface.OnClickListener {

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
    public static final int START_STATS_DEFAULT = 2;
    public static final int STATS_TO_PURCHASE = 10;
    public static final int OUTPOST = 1;
    public static final int TAVERN = 6;
    public static final int TOWER = 10;
    public static final int FORTRESS = 12;
    public static final int CASTLE = 15;

    boolean stopper;
    int step, timeToWait, stepLocation, counterArena;

    int outpostAnswer;

    int ownerOutpost, ownerTavern, ownerTower, ownerFortress, ownerCastle;

    TextView tvTopName, tvHp, tvBotName1, tvHp1, tvBotName2, tvHp2, tvGoldShow, tvExp;
    Button ivDice, btnLvlPlusStrength, btnLvlPlusVitality, btnLvlPlusAgility, btnLvlPlusFocus;
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

    int hpBeforeFight, hpMaxBeforeFight;
    int maxExperience, experience, lvl;

    DialogFragment outpostFragment;

    private String[] nameArray = {"Asshole", "Cock", "Dick", "Tits", "Bobby", "Hump", "Bravo", "super", "black", "gammy"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        //container = (FrameLayout) findViewById(R.id.container);

        btnLvlPlusVitality = (Button) findViewById(R.id.btnLvlPlusVit);
        btnLvlPlusStrength = (Button) findViewById(R.id.btnLvlPlusStrength);
        btnLvlPlusAgility = (Button) findViewById(R.id.btnLvlPlusAgility);
        btnLvlPlusFocus = (Button) findViewById(R.id.btnLvlPlusFocus);


        outpostFragment = new OutpostFragment();

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
        tvGoldShow = (TextView) findViewById(R.id.goldShow);
        tvTopName = (TextView) findViewById(R.id.tvTopName);
        tvHp = (TextView) findViewById(R.id.tvHP);
        tvExp = (TextView) findViewById(R.id.tvExp);

        //Views for bots
        tvBotName1 = (TextView) findViewById(R.id.tvBotName1);
        tvHp1 = (TextView) findViewById(R.id.tvHP1);
        tvBotName2 = (TextView) findViewById(R.id.tvBotName2);
        tvHp2 = (TextView) findViewById(R.id.tvHP2);

        // creating hero
        final HeroClass player = new HeroClass();
        creatorHero(player);
        //__________________________________________________________________________________________


        //Creating bot1
        final HeroClass bot1 = new HeroClass(randomName(), START_STATS_DEFAULT,
                START_STATS_DEFAULT, START_STATS_DEFAULT, START_STATS_DEFAULT);
        creatorBot(bot1, STATS_TO_PURCHASE);
        fillTextBot1(bot1);
        //__________________________________________________________________________________________

        //Creating bot2
        HeroClass bot2 = new HeroClass(randomName(), START_STATS_DEFAULT, START_STATS_DEFAULT,
                START_STATS_DEFAULT, START_STATS_DEFAULT);
        creatorBot(bot2, STATS_TO_PURCHASE);
        fillTextBot2(bot2);
        //__________________________________________________________________________________________


        final HouseClass outpost = new HouseClass(0, 20, 3, "outpost across a river");
        ownerOutpost = outpost.getOwner();

        final HouseClass tavern = new HouseClass(0, 27, 5, "tavern 'Small pony'");
        ownerTavern = tavern.getOwner();

        final HouseClass tower = new HouseClass(0, 35, 7, "eagle tower");
        ownerTower = tower.getOwner();

        final HouseClass fortress = new HouseClass(0, 42, 10, "fortress of free people");
        ownerFortress = fortress.getOwner();

        final HouseClass castle = new HouseClass(0, 50, 15, "castle of the knights");
        ownerCastle = castle.getOwner();

        ivDice = (Button) findViewById(R.id.ivDice);

        ivDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Handler handler = new Handler();
                randomAnimation();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        stepController(player, step);

                        stepAction(player, outpost, tavern, tower, fortress, castle);

                        player.setHp(hpBeforeFight);
                    }
                }, timeToWait);
            }
        });
    }




    public void saveOutpostAnswer (int i){
        outpostAnswer = i;
    }

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
    public void randomAnimation() {

        final int count = randomDiceCount();

        final Handler handler = new Handler();

        final Runnable updateDice = new Runnable() {
            @Override
            public void run() {
                step = randomKubThreadLast();
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

    public int randomKubThreadLast() {
        int min = 1;
        int max = 6;
        Random random = new Random();
        int randomDice = random.nextInt(max - min + 1) + min;
        image(randomDice);
        return randomDice;

    } //return number of dice or just change picture

    public void image(int random) {
        if (random == 1) {
            ivDice.setBackgroundResource(R.drawable.aone);
        } else if (random == 2) {
            ivDice.setBackgroundResource(R.drawable.atwo);
        } else if (random == 3) {
            ivDice.setBackgroundResource(R.drawable.athree);
        } else if (random == 4) {
            ivDice.setBackgroundResource(R.drawable.afour);
        } else if (random == 5) {
            ivDice.setBackgroundResource(R.drawable.afive);
        } else if (random == 6) {
            ivDice.setBackgroundResource(R.drawable.asix);
        }

    } // just show needed picture
    //______________________________________________________________________________________________

    //Creates bots (bot1 or bot2)

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

    //its deleting, check for round and drawing new dot
    public void stepController(HeroClass heroClass, int step) {
        stepDotPlayerDeleteOld(heroClass, step);
    } //Main method, called from here

    public void stepChecker(HeroClass heroClass, int step) {
        heroClass.setStep(heroClass.getStep() + step);
        if (heroClass.getStep() > STEP_MAX) {
            heroClass.setStep(heroClass.getStep() - STEP_MAX);
            heroClass.setHp((hpMaxBeforeFight * 2 / 5) + heroClass.getHp());
            if (heroClass.getHp() > hpMaxBeforeFight) heroClass.setHp(hpMaxBeforeFight);
            tvHp.setText(getResources().getString(R.string.hp_bar,
                    String.valueOf(heroClass.getHp()), String.valueOf(hpMaxBeforeFight)));
            heroClass.setGold(heroClass.getGold() + 10);
            hpBeforeFight = heroClass.getHp();
            tvGoldShow.setText(String.valueOf(heroClass.getGold()));
            heroClass.setExp(heroClass.getExp() + 100 * heroClass.getRoundCount());
            expChecker(heroClass);

        }
        //Toast.makeText(GameMap.this, " " + heroClass.getStep(), Toast.LENGTH_SHORT).show();
        stepLocation = heroClass.getStep();

        stepDotPlayerNew(heroClass);
    }

    public void stepDotPlayerDeleteOld(HeroClass heroClass, int step) {

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

        stepChecker(heroClass, step);
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

            outpostFragment.show(getFragmentManager(), "outpostFragment");
            //houseAction(heroClass, outpost);

        } else if (heroClass.getStep() == 3) {

            senderStats(heroClass, MONSTER_EASY, MONSTER_BONUS_NULL);

        } else if (heroClass.getStep() == 4) {

        } else if (heroClass.getStep() == 5) {

            senderStats(heroClass, MONSTER_MEDIUM, MONSTER_BONUS_NULL);

        } else if (heroClass.getStep() == TAVERN) {

            houseAction(heroClass, tavern);

        } else if (heroClass.getStep() == 7) {

        } else if (heroClass.getStep() == 8) {

            senderStats(heroClass, MONSTER_EASY, MONSTER_BONUS_NULL);

        } else if (heroClass.getStep() == 9) {

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

    public void houseAction(final HeroClass heroClass, final HouseClass houseClass) {

        @SuppressLint("ValidFragment") final
        DialogFragment fightResult = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                setCancelable(false);
                builder.setMessage(getResources().getString(R.string.what_house, houseClass.getName()));
                builder.setTitle(getResources().getString(R.string.path));
                builder.setIcon(R.drawable.tavernalert);

                if (houseClass.getOwner() == 0) {
                    builder.setPositiveButton(getResources().getString(R.string.action_buy, String.valueOf(houseClass.getHouseCost())),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    buttonBuy(heroClass, houseClass);
                                }
                            });

                }


                if (houseClass.getOwner() == 1) {
                    builder.setPositiveButton(getResources().getString(R.string.at_home, heroClass.getName()),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    heroClass.setHp(heroClass.getHp() * 2 / 5 + heroClass.getHp());
                                    if (heroClass.getHp() > hpMaxBeforeFight)
                                        heroClass.setHp(hpMaxBeforeFight);
                                    tvHp.setText(getResources().getString(R.string.hp_bar,
                                            String.valueOf(heroClass.getHp()), String.valueOf(hpMaxBeforeFight)));

                                }
                            });

                } else {
                    builder.setNeutralButton(R.string.pay_pass,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    buttonPayPass(heroClass, houseClass);

                                }
                            });

                    builder.setNegativeButton(R.string.break_through,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    senderStats(heroClass, MONSTER_HARD, MONSTER_BONUS_STRENGTH_LOW);
                                }
                            });

                }


                return builder.create();
            }
        };
        fightResult.show(getFragmentManager(), "fight result");
    }

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

    public void arenaAction(final HeroClass heroClass) {

        @SuppressLint("ValidFragment") final
        DialogFragment fightResult = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                //View view = getActivity().getLayoutInflater().inflate(R.layout.arena_activity, null);
                //builder.setView(view);
                setCancelable(false);
                builder.setMessage(getResources().getString(R.string.arena_message));
                builder.setTitle(getResources().getString(R.string.arena_title));
                builder.setIcon(R.drawable.arena);


                builder.setPositiveButton(getResources().getString(R.string.stop_fight),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //senderStats(heroClass, MONSTER_EASY);
                                stopper = true;
                                dismiss();
                            }
                        });


                builder.setNeutralButton(getResources().getString(R.string.average_monster),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                senderStats(heroClass, MONSTER_MEDIUM, MONSTER_BONUS_STRENGTH_MEDIUM);

                            }
                        });

                builder.setNegativeButton(getResources().getString(R.string.strong_monster), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        senderStats(heroClass, MONSTER_HARD, MONSTER_BONUS_STRENGTH_HARDCORE);
                    }
                });

                return builder.create();
            }


        };
        fightResult.show(getFragmentManager(), "fight result");
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
