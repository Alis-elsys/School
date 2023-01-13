package users;



public class UserDetails {
    private final int id;
    private static int count = 100;
    private String firstName;
    private String lastName;
    private long telephone;
    private final int userid;


    public UserDetails(String firstName, String lastName, long telephone, int userid) {
        id = count;
        count++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.userid = userid;
    }


    //getters
    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }
    public long getTelephone(){ return telephone; }
    public int getUserid(){ return userid; }
    //setters
    public void setFirstName(String firstName){ this.firstName = firstName; }
    public void setLastName(String lastName){ this.lastName = lastName; }
    public void setTelephone(long telephone){ this.telephone = telephone; }

}
