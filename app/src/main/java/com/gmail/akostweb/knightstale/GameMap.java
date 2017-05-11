package com.gmail.akostweb.knightstale;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface.OnClickListener;
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

    boolean stopper;
    int step, timeToWait, stepLocation, counterArena;

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

        tvGoldShow = (TextView) findViewById(R.id.goldShow);

        final HeroClass player = new HeroClass();
        creatorHero(player);
        setStats(player);
        player.setRoundCount(0);
        player.setGold(200);
        player.setExp(0);
        iv11.setBackgroundResource(R.drawable.shieldsuperthumb);
        hpBeforeFight = player.getHp();
        hpMaxBeforeFight = player.getHp();
        tvGoldShow.setText(String.valueOf(player.getGold()));
        tvTopName = (TextView) findViewById(R.id.tvTopName);
        tvTopName.setText(player.getName());
        lvl = 1;
        maxExperience = 1000 * lvl;
        experience = 0;
        tvExp = (TextView) findViewById(R.id.tvExp);
        experience();
        tvHp = (TextView) findViewById(R.id.tvHP);
        tvHp.setText(getResources().getString(R.string.hp_bar,
                String.valueOf(player.getHp()), String.valueOf(player.getHp())));


        tvBotName1 = (TextView) findViewById(R.id.tvBotName1);
        tvHp1 = (TextView) findViewById(R.id.tvHP1);
        final HeroClass bot1 = new HeroClass(randomName(), 2, 2, 2, 2);
        creatorBot(bot1, 10);
        setStats(bot1);
        bot1.setStep(0);
        tvBotName1.setText(bot1.getName());
        tvHp1.setText(getResources().getString(R.string.hp_bar,
                String.valueOf(bot1.getHp()), String.valueOf(bot1.getHp())));
        bot1.setRoundCount(0);


        tvBotName2 = (TextView) findViewById(R.id.tvBotName2);
        tvHp2 = (TextView) findViewById(R.id.tvHP2);
        HeroClass bot2 = new HeroClass(randomName(), 2, 2, 2, 2);
        creatorBot(bot2, 10);
        setStats(bot2);
        bot2.setStep(0);
        tvBotName2.setText(bot2.getName());
        tvHp2.setText(getResources().getString(R.string.hp_bar,
                String.valueOf(bot2.getHp()), String.valueOf(bot2.getHp())));
        bot2.setRoundCount(0);


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

//сделать точку отправления

        ivDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Handler handler = new Handler();
                randomAnimation();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        stepController(player, step);

                        stepAction(player, outpost, tavern, tower, fortress, castle);

                        if (hpBeforeFight <= 0) endGame();
                        player.setHp(hpBeforeFight);

                    }
                }, timeToWait);

            }


        });


    }

    public void experience() {
        tvExp.setText(getResources().getString(R.string.experience, String.valueOf(experience),
                String.valueOf(maxExperience)));

    }

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
    }

    public void buttonPayPass(HeroClass heroClass, HouseClass houseClass) {
        if (heroClass.getGold() >= 3) {
            heroClass.setGold(heroClass.getGold() - houseClass.costToPass);
            tvGoldShow.setText(String.valueOf(heroClass.getGold()));
        } else {
            senderStats(heroClass, MONSTER_MEDIUM, MONSTER_BONUS_STRENGTH_LOW);
        }


    }

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
        if (hpBeforeFight <= 0) endGame();
        Toast.makeText(this, hpBeforeFight + " ", Toast.LENGTH_SHORT).show();
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

    }


    public void expChecker(HeroClass heroClass) {
        if (heroClass.getExp() >= 1000) {
            heroClass.setExp(0);

        }
    }

    public void randomKubThread() {
        int min = 1;
        int max = 6;
        Random random = new Random();
        int randomDice = random.nextInt(max - min + 1) + min;
        image(randomDice);

    }

    public int randomKubThreadLast() {
        int min = 1;
        int max = 6;
        Random random = new Random();
        int randomDice = random.nextInt(max - min + 1) + min;
        image(randomDice);
        return randomDice;

    }

    public int randomDiceCount() {
        int min = 10;
        int max = 11;

        Random random = new Random();
        int s = random.nextInt(max - min + 1) + min;
        timeToWait = s * 185;
        return s;

    }

    public void randomAnimation() {

        final int count = randomDiceCount();

        final Handler handler = new Handler();

        final Runnable lastDice = new Runnable() {
            @Override
            public void run() {
                step = randomKubThreadLast();
            }
        };

        final Runnable updateDice = new Runnable() {
            @Override
            public void run() {
                randomKubThread();
            }
        };

        final Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= count; i++) {
                    if (i != count) {
                        handler.post(updateDice);
                        try {
                            Thread.sleep(300 - (285 / count) * i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        handler.post(lastDice);
                    }

                }

            }
        };
        thread.start();

    }

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

    public void creatorBot(HeroClass heroClass, int count) {

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


    }

    public void setStats(HeroClass hero) {
        hero.setHp(hero.vitality * 500 + hero.strength * 100);
        hero.setDamage(hero.strength * 40 + hero.vitality * 10);
        hero.setCrit(hero.focus * 9);
        hero.setEvasion(hero.agility * 6);
        hero.setStep(1);
        hero.setRoundCount(0);

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    //________________________________________________________________
    //its deleting, check for round and drawing new dot
    public void stepController(HeroClass heroClass, int step) {
        stepDotPlayerDeleteOld(heroClass, step);
    }

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
    // ________________________________________________________________

    // Fighting after turn or using houseClass:
    // - Action for step dot(House action or fight action)
    // Fighting action:
    // 1. sendStats to fightActivity
    // 2.
    //
    public void stepAction( HeroClass heroClass, HouseClass outpost, HouseClass tavern,
                            HouseClass tower,HouseClass fortress,HouseClass castle) {
        if (heroClass.getStep() == 1) {
            heroClass.setRoundCount(heroClass.getRoundCount() + 1);
            heroClass.setGold(heroClass.getGold() + 5 * heroClass.getRoundCount());

        } else if (heroClass.getStep() == 2) {

            houseAction(heroClass, outpost);

        } else if (heroClass.getStep() == 3) {

            senderStats(heroClass, MONSTER_EASY, MONSTER_BONUS_NULL);

        } else if (heroClass.getStep() == 4) {

        } else if (heroClass.getStep() == 5) {

            senderStats(heroClass, MONSTER_MEDIUM, MONSTER_BONUS_NULL);

        } else if (heroClass.getStep() == 6) {

            houseAction(heroClass, tavern);

        } else if (heroClass.getStep() == 7) {

        } else if (heroClass.getStep() == 8) {

            senderStats(heroClass, MONSTER_EASY, MONSTER_BONUS_NULL);

        } else if (heroClass.getStep() == 9) {

        } else if (heroClass.getStep() == 10) {

            houseAction(heroClass, tower);

        } else if (heroClass.getStep() == 11) {

            senderStats(heroClass, MONSTER_HARD, MONSTER_BONUS_NULL);

        } else if (heroClass.getStep() == 12) {

            houseAction(heroClass, fortress);

        } else if (heroClass.getStep() == 13) {
            stopper = false;
            arenaAction(heroClass);

            if (!stopper) arenaAction(heroClass);

            if (!stopper) arenaAction(heroClass);


        } else if (heroClass.getStep() == 14) {

            senderStats(heroClass, MONSTER_MEDIUM, MONSTER_BONUS_NULL);

        } else if (heroClass.getStep() == 15) {

            houseAction(heroClass, castle);

        } else if (heroClass.getStep() == 16) {

            senderStats(heroClass, MONSTER_HARD, MONSTER_BONUS_NULL);

        }
    }

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
        hpMaxBeforeFight = heroClass.vitality * 500 + heroClass.strength * 100;
        startActivityForResult(intent, 1);

    }

    //______________________________________________________________________
}
