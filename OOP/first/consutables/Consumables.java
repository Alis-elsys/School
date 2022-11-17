package Homeworks.first.consutables;

import Homeworks.first.Hero;
import Homeworks.first.Object;

public abstract class Consumables extends Object {
    private int level;

    public Consumables(char initial, int level) {
        if(level < 1 || level > 5){
            throw new IllegalArgumentException("Level must be between 1 and 5");
        }
        this.initials = initial + " ";
        this.level = level;
    }

    public int getLevel(){ return level; }
    public abstract void apply(Hero hero, int level);

}


