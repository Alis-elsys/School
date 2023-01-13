package users;

import java.util.ArrayList;


public class User {
    private int id;
    private static int count = 1;
    private String username;
    //list of all created users name
    private static ArrayList<String> usersName = new ArrayList<String>();
    private String password;
    private Role role;



    public User(String username, String password, Role role){
        if(username == ""){
            throw new IllegalArgumentException("Username can't be empty!");
        }
        if(password == ""){
            throw new IllegalArgumentException("Password can't be empty!");
        }

        this.id = count;
        count++;
        this.username = username;
        usersName.add(username);
        this.password = password;
        this.role = role;
    }

    public User(String username, String password){
        this.id = count;
        count++;

        if(password == ""){
            throw new IllegalArgumentException("Password can't be empty!");
        }

        this.username = username;
        usersName.add(username);
        this.password = password;
        this.role = Role.CUSTOMER;
    }

    //getters
    public int getId(){ return id; }
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public Role getRole(){ return role; }
    //setters
    public void setUsername(String username){ this.username = username; }
    public void setPassword(String password){ this.password = password; }
    public void setRole(Role role){ this.role = role; }

}
