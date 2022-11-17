package Homeworks.first;

import Homeworks.first.consutables.*;

public class Main {

    public static void main(String[] args) {
        Field field = new Field(4, 3);
        System.out.print("Field with size 5 x 5 has been initialized.\n");
        Object hero1 = new Hero("Thor", "Th", 2000, 300);
        Object hero = new Hero("Gosho", "G", 1500, 200);
        Consumables brokoli = new Brokoli(1);
        Consumables orange = new Orange(2);
        Consumables rise = new Rice(5);
        Consumables whiskey = new Whiskey(3);
        ((Hero)hero1).printHero();
        ((Hero)hero).printHero();
        field.addHero((Hero)hero);
        field.addHero((Hero)hero1);

        field.addConsumable(brokoli);
        field.addConsumable(orange);
        field.addConsumable(rise);
        field.addConsumable(whiskey);

        System.out.println();
        while(true){
            for(int i = 0; i < field.heroes.length; i++) {
                ((Hero) hero).heroTurn(field);
                if (field.haveWinner()) {
                    break;
                }
                ((Hero) hero1).heroTurn(field);
                if (field.haveWinner()) {
                    break;
                }
            }
        }
    }
}
