package Homeworks.first;

import Homeworks.first.consutables.Consumables;
import Homeworks.first.consutables.Whiskey;
import Homeworks.first.consutables.Mushroom;

public class Field {
    private final Object[][] field;
    public Hero[] heroes;
    public int size;
    public final int Y;
    public final int X;

    public Field(int Y, int X){
        if(Y < 0 || X < 0){
            throw new IllegalArgumentException("Y and X must be positive");
        }
        this.field = new Object[Y][X];
        this.Y = Y;
        this.X = X;
        this.heroes = new Hero[Y * X];
        this.size = 0;
    }

    public void removeHeroFromField(Hero hero){
        field[Y-hero.y][hero.x-1] = null;
    }

    public void removeHero(Hero hero){
        for(int i = 0; i < heroes.length; i++){
            if(heroes[i] == hero){
                heroes[i] = null;
                size--;
            }
        }
    }

    public boolean haveWinner(){
        if(size == 1){
            for(int i = 0; i < heroes.length; i++){
                if(heroes[i] != null){
                    System.out.println(heroes[i].getName() + " is the winner!");
                    return true;
                }
            }
        }
        return false;
    }

    public void addHero(Hero hero){
        int x, y;
        x = (int)(Math.random() * X);
        y = (int)(Math.random() * Y);
        System.out.print("random x = "+x+"random y = "+y+"\n");
        if(field[y][x] != null){
            addHero(hero);
        }else{
            field[y][x] = hero;
            hero.x = x+1;
            hero.y = Y-y;
            for(int i = 0; i < heroes.length; i++){
                if(heroes[i] == null){
                    heroes[i] = hero;
                    size++;
                    break;
                }
            }
            System.out.print("Hero added Hero(name=" + hero.getName() +
            ", initial= " + hero.initials + ", health=" + hero.getHealth() +
            ", power=" + hero.getPower() + ") at x=" + hero.x + ", y=" + hero.y + "\n");
        }
    }

    public void addConsumable(Consumables consumable){
        int x = (int)(Math.random() * X);
        int y = (int)(Math.random() * Y);
        System.out.print("Food random x = "+x+"  random y = "+y+"\n");
        if(field[y][x] != null){
            addConsumable(consumable);
        }else{
            field[y][x] = consumable;
            consumable.x = x+1;
            consumable.y = Y-y;
            System.out.print("Consumable added Consumable(initial=" +
            consumable.initials +" level=" + consumable.getLevel() +
            ") at x=" + consumable.x + ", y=" + consumable.y + "\n");
        }
    }

    public void moveHero(Hero hero){
        int direction = Direction.getRandomDirection();
        int newX, newY, oldX, oldY;
        oldX = hero.x-1;
        oldY = Y-hero.y;

        if(direction == Direction.UP){
            System.out.print(hero.getName() + " moves UP\n");
            if(hero.y == Y){
                newY = oldY;
                return;
            }else{
                newY = Y - (hero.y + 1);
                hero.y++;
            }
            newX = oldX;
        }else if(direction == Direction.DOWN){
            System.out.print(hero.getName() + " moves DOWN\n");
            if(hero.y == 1){
                newY = oldY;
                return;
            }else{
                newY = Y - (hero.y - 1);
                hero.y--;
            }
            newX = oldX;
        }else if(direction == Direction.LEFT){
            System.out.print(hero.getName() + " moves LEFT\n");
            if(hero.x == 1){
                newX = oldX;
                return;
            }else{
                newX = hero.x - 2;
                hero.x--;
            }
            newY = oldY;
        }else{
            System.out.print(hero.getName() + " moves RIGHT\n");
            if(hero.x == X){
                newX = oldX;
                return;
            }else{
                newX = hero.x;
                hero.x++;
            }
            newY = oldY;
        }

        if(field[newY][newX] != null){
            if(field[newY][newX] instanceof Hero){
                Hero newHero = (Hero) field[newY][newX];
                hero.attack(newHero);
                if(hero.getHealth() == 0){
                    removeHeroFromField(hero);
                    System.out.println(hero.getName() + " died");
                    
                    removeHero(hero);
                    
                    if(haveWinner()){
                        return;
                    }
                }
                if(newHero.getHealth() == 0){
                    field[newY][newX] = hero;
                    field[oldY][oldX] = null;
                    System.out.println(newHero.getName() + " died");
                    
                    removeHero(newHero);

                    if(haveWinner()){
                        return;
                    }
                }
                if(size == 0){
                    System.out.print("No winner");
                }
            }else{
                Consumables consumable = (Consumables) field[newY][newX];
                if(consumable instanceof Whiskey){
                    hero.poisonFood[hero.pCounter+1] = consumable;
                    hero.pCounter++;
                }
                else if(consumable instanceof Mushroom){
                    hero.poisonFood[hero.pCounter+1] = consumable;
                    hero.pCounter++;
                }else{

                }
                consumable.apply(hero, consumable.getLevel());

                field[newY][newX] = hero;
                field[oldY][oldX] = null;
            }
        }else{
            field[newY][newX] = field[oldY][oldX];
            field[oldY][oldX] = null;
        }
    }

    //method to print the field
    public void printField(){
        for(int i = 0; i < field.length; i++){
            System.out.print("|");
            for(int j = 0; j < field[i].length; j++){
                if(field[i][j] == null){
                    System.out.print("|  |");
                }else{
                    System.out.print("|" + field[i][j].initials + "|");
                }
            }
            System.out.print("|\n");
        }
    }
}