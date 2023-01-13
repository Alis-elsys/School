package packages;

public class SalablePackage extends Package {
    private Package type;
    private int price;

    public SalablePackage(int size, int price) {
        if (size > 9 || size < 0 || price < 0) {
            System.out.println("Invalid size or price!");
        } else {
            if (size <= 3) {
                this.type = new SmallSize(size);
            } else if (size <= 6) {
                this.type = new MiddleSize(size);
            } else {
                this.type = new BigSize(size);
            }
            this.size = size;
            this.price = price;
        }
    }

    @Override
    public double getDeliveryPrice(){
        if(type != null)
            return type.getDeliveryPrice() + price * 0.1;
        else
            return 0;
    }
}
