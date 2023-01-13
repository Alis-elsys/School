package packages;

public class SmallSize extends Package {

    public SmallSize(int size) {
        if(size < 4 && size > 0)
            this.size = size;
        else
            System.out.println("Invalid size!");
    }

    @Override
    public double getDeliveryPrice(){
        return size * 3;
    }

}
