package packages;

public class BigSize extends Package {
    public BigSize(int size) {
        if(size < 10 && size > 6)
            this.size = size;
        else
            System.out.println("Invalid size!");
    }

    @Override
    public double getDeliveryPrice(){
        return size * 5;
    }

}

