package Homeworks.first.consutables;

import Homeworks.first.Hero;

public class Rice extends Consumables {
    public Rice(int level) {
        super('R', level);
    }
    @Override
    public void apply(Hero hero, int level){
        hero.setPower(hero.getPower() + 2 * level);
        System.out.println(hero.getName()+ " consumed Rice");
        hero.printHero();
    }
}