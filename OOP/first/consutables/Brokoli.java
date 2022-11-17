package Homeworks.first.consutables;

import Homeworks.first.Hero;
public class Brokoli extends Consumables {
    public Brokoli(int level) {
        super('B', level);
    }
    @Override
    public void apply(Hero hero, int level){
        hero.setHealth(hero.getHealth() + 2 * level);
        System.out.println(hero.getName()+ " consumed Brokoli");
        hero.printHero();
    }
}
