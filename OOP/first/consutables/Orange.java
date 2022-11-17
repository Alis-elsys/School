package Homeworks.first.consutables;

import Homeworks.first.Hero;

public class Orange extends Consumables {
    public Orange(int level) {
        super('O', level);
    }
    @Override
    public void apply(Hero hero, int level){
        hero.setHealth(hero.getHealth() + level);
        hero.setPower(hero.getPower() + level);
        System.out.println(hero.getName()+ " consumed Orange");
        hero.printHero();
    }
}
