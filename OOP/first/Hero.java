package Homeworks.first;

import Homeworks.first.consutables.*;

public class Hero extends Object {
    private final String name;
    private int health;
    private int power;
    public Consumables[] poisonFood;
    public int pCounter;


    public Hero(String name, String initials, int health, int power) {
        if(health < 0 || power < 0){
            throw new IllegalArgumentException("Health and power must be positive");
        }
        if(name.length() < 2){
            throw new IllegalArgumentException("Name must be at least two letters");
        }
        this.name = name;
        this.initials = name.substring(0, 2);
        this.health = health;
        this.power = power;
        this.poisonFood = new Consumables[2];
        this.pCounter = 0;
    }

    public String getName(){ return name; }
    public int getHealth(){ return health; }
    public int getPower(){ return power; }
    public void setHealth(int health){ this.health = health; }
    public void setPower(int power){ this.power = power; }


    public boolean isDead(){
        return health <= 0;
    }
    public void attack(Hero hero){
        if(this.power == hero.getPower()){
            if(this.health == hero.getHealth()){
                this.health = 0;
                hero.setHealth(0);

            }else if(this.health < hero.getHealth()){
                this.health = 0;
            }else{
                hero.setHealth(0);
            }
        }else if(this.power < hero.getPower()){
            this.health = 0;
        }else{
            hero.setHealth(0);
        }
    }
    public void heroTurn(Field field){
        if(this.pCounter > 0){
            for(int i = 1; i < this.pCounter+1; i++){
               this.poisonFood[i].apply(this, this.poisonFood[i].getLevel());
           }
       }
       if(this.isDead()){
           field.removeHeroFromField(this);
           field.removeHero(this);
           if(field.size == 1){
                System.exit(0);
           }
       }
        field.printField();
        field.moveHero(this);
    }
    public void printHero(){
        System.out.println("Hero(name=" + name + ", initial= " + initials + ", health=" + health +
                ", power=" + power + ")");
    }
}
