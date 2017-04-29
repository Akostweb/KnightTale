package com.gmail.akostweb.knightstale;

/**
 * Created by Andrew on 16.03.2017.
 */

public class HouseClass {

    public int owner;
    public int houseCost;
    public int costToPass;
    public String name;

    public HouseClass(int owner, int houseCost, int costToPass, String name) {
        this.owner = owner;
        this.houseCost = houseCost;
        this.costToPass = costToPass;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getHouseCost() {
        return houseCost;
    }

    public void setHouseCost(int houseCost) {
        this.houseCost = houseCost;
    }

    public int getCostToPass() {
        return costToPass;
    }

    public void setCostToPass(int costToPass) {
        this.costToPass = costToPass;
    }
}
