package packages;

public abstract class Package {
    private final int id;
    protected int size;

    private static int count = 1000;

    public Package(){
        id = count;
        count++;
    }

    public int getId(){ return id; }
    public abstract double getDeliveryPrice();
}
