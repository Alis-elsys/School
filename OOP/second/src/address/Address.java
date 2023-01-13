package address;

public class Address {
    //id country city street userID
    private final int id;
    private int count = 10;
    private String country;
    private String city;
    private String street;
    private final int userid;

    public Address(String country, String city, String street, int userid) {
        id = count;
        count++;
        this.country = country;
        this.city = city;
        this.street = street;
        this.userid = userid;
    }

    //getters
    public int getId(){ return id; }
    public String getCountry(){ return country; }
    public String getCity(){ return city; }
    public String getStreet(){ return street; }
    public int getUserId(){ return userid; }
    //setters
    public void setCountry(String country){ this.country = country; }
    public void setCity(String city){ this.city = city; }
    public void setStreet(String street){ this.street = street; }


}
