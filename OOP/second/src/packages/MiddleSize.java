package packages;

public class MiddleSize extends Package {
    public MiddleSize(int size) {
        if(size < 7 && size > 3)
            this.size = size;
        else
            System.out.println("Invalid size!");
    }

    @Override
    public double getDeliveryPrice(){
        return size * 4;
    }

}
