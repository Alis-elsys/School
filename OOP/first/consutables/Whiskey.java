package Homeworks.first.consutables;

import Homeworks.first.Hero;

public class Whiskey extends Consumables {
    public int poison;

    public Whiskey(int level) {
        super('W', level);
        poison = level;
    }
    @Override
    public void apply(Hero hero, int level) {
        if (poison > 0){
            hero.setPower(hero.getPower() - 15);
            System.out.println(hero.getName() + " consumed Whiskey");
            hero.printHero();
            poison--;
        }
    }
}