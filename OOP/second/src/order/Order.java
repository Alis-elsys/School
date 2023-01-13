package order;

import java.util.ArrayList;

import packages.Package;

public class Order {
    private final int id;
    private static int count = 1000;
    private ArrayList <Package> packages = new ArrayList<Package>();
    private final int addressId;
    private Status status;


    public Order(int addressId) {
        id = count;
        count++;
        this.addressId = addressId;
        this.status = Status.CREATED;
        this.packages.addAll(packages);
    }

    //getters
    public int getId(){ return id; }
    public int getAddressId(){ return addressId; }
    public Status getStatus(){ return status; }
    public ArrayList<Package> getPackages(){ return packages; }

    //setters
    public void setStatus(Status status){ this.status = status; }

    public int getDeliveryPrice(){
        int sum = 0;
        for (Package p : packages) {
            sum += p.getDeliveryPrice();
        }
        return sum;
    }

    public void addPackage(Package pack){
        packages.add(pack);
    }
}
