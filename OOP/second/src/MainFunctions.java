//import address.Address;
//import packages.Package;
//import users.Role;
//import users.User;
//import packages.*;
//import order.*;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class MainFunctions {
//    private List<User> users;
//    private User currentUser;
//    private List<Order> orders;
//    private List<Address> addresses;
//    private List<Package> packages;
//
//
//    public MainFunctions(){
//        this.users = new ArrayList<User>();
//        this.currentUser = null;
//    }
//
//    //getters
//    public List<User> getUsers() { return this.users; }
//    public User getCurrentUser() { return this.currentUser; }
//
//    //setters
//    public void setCurrentUser(User currentUser) { this.currentUser = currentUser; }
//
//    //add
//    public void addUser(User user){ this.users.add(user); }
//
//    //delete
//    public void deleteUser(User user){ this.users.remove(user); }
//
//    //login
//    public void login(String username, String password){
//        for(int i = 0; i < this.users.size(); i++){
//            if(this.users.get(i).getUsername().equals(username) && this.users.get(i).getPassword().equals(password)){
//                this.currentUser = this.users.get(i);
//                return;
//            }
//        }
//        System.out.println("Wrong name or password!");
//    }
//
//    //logout
//    public void logout(){
//        this.currentUser = null;
//    }
//
//    //register
//    public void register(String username, String password, String role){
//        if(role.equals("ADMINISTRATOR")){
//            this.users.add(new User(username, password, Role.ADMINISTRATOR));
//        }else if(role.equals("CUSTOMER")){
//            this.users.add(new User(username, password, Role.CUSTOMER));
//        }else if(role.equals("DRIVER")){
//            this.users.add(new User(username, password, Role.DRIVER));
//        }else{
//            System.out.println("Wrong role!");
//        }
//    }
//
//    // add functions for order, package, address
//    public void addOrder(){
//
//    }
//
//    public void addPackage(Package pack){
//        this.currentUser.addPackage(pack);
//    }
//
//    public void addAddress(String country, String city, String street, int userid){
//        Address newAddress = new Address(country, city, street, userid);
//        this.addresses.add(newAddress);
//    }
//
//}
