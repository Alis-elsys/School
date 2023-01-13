import users.Role;
import users.User;
import packages.Package;
import address.Address;
import order.*;

import java.util.ArrayList;

public class DeliverySystem {
    //collection of users
    ArrayList<User> users = new ArrayList<>();
    //collection of orders
    ArrayList<Order> orders = new ArrayList<>();
    //collection of addresses
    ArrayList<Address> addresses = new ArrayList<>();
    //current user
    User currentUser;


    public DeliverySystem() {
        users.add(new User("admin", "admin", Role.ADMINISTRATOR));
        currentUser = null;
    }

    public DeliverySystem(ArrayList<User> users, ArrayList<Order> orders, ArrayList<Address> addresses) {
        this.users.add(new User("admin", "admin", Role.ADMINISTRATOR));
        this.users.addAll(users);
        this.orders.addAll(orders);
        this.addresses.addAll(addresses);
        currentUser = null;

    }

    boolean is_not_logged() {
        if (currentUser == null){
            System.out.println("You are not logged in!");
            return true;
        }
        return false;
    }

    boolean is_not_admin() {
        if (currentUser.getRole() != Role.ADMINISTRATOR){
            System.out.println("You have to be admin!");
            return true;
        }
        return false;
    }

    boolean is_not_customer() {
        if (currentUser.getRole() != Role.CUSTOMER){
            System.out.println("You have to be client!");
            return true;
        }
        return false;
    }

    boolean is_not_driver() {
        if (currentUser.getRole() != Role.DRIVER){
            System.out.println("You have to be driver!");
            return true;
        }
        return false;
    }

    void login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                return;
        }
    }
        System.out.println("Wrong username or password!");
    }

    void logout() {
        currentUser = null;
    }

    void registerUser(String username, String password) {
        if (is_not_logged())
            return;

        if (is_not_admin())
            return;

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists");
                return;
            }
        }
        if (username == "") {
            System.out.println("Username can't be empty!");
            return;
        }
        if (password == "") {
            System.out.println("Password can't be empty!");
            return;
        }
        users.add(new User(username, password));
    }

   void registerDriver(String username, String password){
        if(is_not_logged())
            return;

        if(is_not_admin())
            return;

        for(User user : users){
            if(user.getUsername().equals(username)){
                System.out.println("Username already exists");
                return;
            }
        }
        if (username == "") {
            System.out.println("Username can't be empty!");
            return;
        }
        if (password == "") {
            System.out.println("Password can't be empty!");
            return;
        }
        users.add(new User(username, password, Role.DRIVER));
   }

    void addAddress(Address address){
        if(is_not_logged())
            return;

        if(is_not_customer())
            return;

        if(currentUser.getId() != address.getUserId()){
            System.out.println("You can only add your own address");
            return;
        }
        addresses.add(address);
    }

    void addOrder(Order order){
        if(is_not_logged())
            return;

        if(is_not_customer())
            return;

        orders.add(order);
    }

    void addPackage(Package pack ,int orderId){
        if(is_not_logged())
            return;

        if(is_not_customer())
            return;

        for(Order order : orders){
            if(order.getId() == orderId){
                for(Address address : addresses){
                    if(currentUser.getId() != address.getUserId()) {
                        System.out.println("You can only add your own package");
                        return;

                    }
                }
            }
        }

        for(Order order : orders){
            if(order.getId() == orderId){
                order.addPackage(pack);
                return;
            }
        }
        System.out.println("Order not found");
    }
    
    void getOrderToDeliver(){
        if(is_not_logged())
            return;

        if(is_not_driver())
            return;

        for(Order order : orders){
            if(order.getStatus() == Status.CREATED){
                order.setStatus(Status.DELIVERING);
                return;
            }
        }
        try{
            throw new Exception("No orders to deliver");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    void getOrderToDeliver(int id){
        if(is_not_logged())
            return;

        if(is_not_driver())
            return;

        for(Order order : orders){
            if(order.getId() == id){
                if(order.getStatus() == Status.CREATED){
                    order.setStatus(Status.DELIVERING);
                    return;
                }else{
                    System.out.println("Order is not in status CREATED");
                    return;
                }
            }
        }
        System.out.println("Order not found");
    }
    void deliverOrder(int id){
        if(is_not_logged())
            return;

        if(is_not_driver())
            return;

        for(Order order : orders){
            if(order.getId() == id){
                if(order.getStatus() == Status.DELIVERING){
                    order.setStatus(Status.DELIVERED);
                    return;
                }else{
                    System.out.println("Order is not in status DELIVERING");
                    return;
                }
            }
        }
        System.out.println("Order not found");
    }


    void printInfo(){
        if(is_not_logged())
            return;
        if(is_not_admin())
            return;


        System.out.println("Users:");
        for(User user : users){
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            System.out.println(user.getId());
        }
        System.out.println("Addresses:");
        for(Address address : addresses){
            System.out.printf(address.getCountry() + " " + address.getCity() + " " + address.getStreet());
        }
        System.out.println("Orders:");
        for(Order order : orders){
            System.out.printf(order.getStatus() + " " + order.getId());
        }
    }

    void printUserInfo(User user, int id){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        for(Address address : addresses){
            if(address.getUserId() == id){
                System.out.printf("Address:");
                System.out.println(address.getCountry() + " " + address.getCity() + " " + address.getStreet());
                for(Order order : orders){
                    if(order.getAddressId() == address.getId()){
                        System.out.printf("Order:");
                        System.out.println(order.getStatus() + " " + order.getId());
                    }
                }
            }
        }
    }

    void printInfo(int id){
        if(is_not_logged())
            return;
        if(is_not_admin())
            return;


        for(User user : users){
            if(user.getId() == id){
                printUserInfo(user, id);
                return;
            }
        }
        try{
            throw new Exception("User not found");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    void printInfo(String username){
        if(is_not_logged())
            return;
        if(is_not_admin())
            return;


        for(User user : users){
            if(user.getUsername().equals(username)){
                printUserInfo(user, user.getId());
                return;
            }
        }
        try{
            throw new Exception("User not found");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    void printInfo(Status status){
        if(is_not_logged())
            return;
        if(is_not_admin())
            return;


        for(Order order : orders){
            if(order.getStatus() == status){
                System.out.println(order.getStatus() + " " + order.getId());
                return;
            }
        }
        try{
            throw new Exception("Order not found");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}

