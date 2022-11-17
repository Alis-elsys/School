package Homeworks.first.consutables;

import Homeworks.first.Hero;
public class Mushroom extends Consumables{
    public int poison;

    public Mushroom(int level) {
        super('M', level);
        poison = level;
    }
    @Override
    public void apply(Hero hero, int level){
     if(poison > 0){
         hero.setHealth(hero.getPower() - 20);
         System.out.println(hero.getName() + " consumed Mushroom");hero.printHero();
         hero.printHero();
         poison--;
        }
    }
}