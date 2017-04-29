package com.gmail.akostweb.knightstale;

/**
 * Created by Администратор on 04.03.2017.
 */

public class HeroClass {

    public String name;
    int vitality;
    int strength;
    int agility;
    int focus;


    public HeroClass() {
    }

    public HeroClass(String name, int vitality, int strength, int agility, int focus, int hp, int roundCount) {
        this.name = name;
        this.vitality = vitality;
        this.strength = strength;
        this.agility = agility;
        this.focus = focus;
        this.hp = hp;
        this.roundCount = roundCount;
    }

    public HeroClass(String name, int vitality, int strength, int agility, int focus) {
        this.name = name;
        this.vitality = vitality;
        this.strength = strength;
        this.agility = agility;
        this.focus = focus;
    }

    private int hp;
    private int damage;
    private int crit;
    private int evasion;
    private int runaway;
    private int block;
    private int armor;
    private int step;
    private int roundCount;
    private int gold;

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    private int exp;

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getFocus() {
        return focus;
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCrit() {
        return crit;
    }

    public void setCrit(int crit) {
        this.crit = crit;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public int getRunaway() {
        return runaway;
    }

    public void setRunaway(int runaway) {
        this.runaway = runaway;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}