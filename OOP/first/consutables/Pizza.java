package Homeworks.first.consutables;

import Homeworks.first.Hero;

public class Pizza extends Consumables {
    public Pizza(int level) {
        super('P', level);
    }
    @Override
    public void apply(Hero hero, int level){
        hero.setPower(hero.getPower() + 13);
        System.out.println(hero.getName()+ " consumed Pizza");
        hero.printHero();
    }
}
