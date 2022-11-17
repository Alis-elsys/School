package Homeworks.first;

public class Direction {
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    public static int getRandomDirection(){
        int random = (int) (Math.random() * 4);

        if(random == UP){
            return UP;
        }else if(random == DOWN){
            return DOWN;
        }else if(random == LEFT){
            return LEFT;
        }else{
            return RIGHT;
        }

    }
}
