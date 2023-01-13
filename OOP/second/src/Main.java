import address.Address;
import order.Order;
import users.Role;
import users.User;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args){
        System.out.println("Hello world!");

        DeliverySystem ds = new DeliverySystem();
        ds.users = new ArrayList<>();
        ds.addresses = new ArrayList<>();
        ds.orders = new ArrayList<>();

        //read users from csv file
        String sample = ",";
        String string;

        try {
            BufferedReader brdrd = new BufferedReader(new FileReader("users2.csv"));
            while ((string = brdrd.readLine()) != null) {
                String[] info = string.split(sample);
                System.out.println(info[0] + " " + info[1] + " " + info[2] + " " + info[3]);
                User u = new User(info[1], info[2], Role.valueOf(info[3]));
                ds.users.add(u);

            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }


        ds.login("admin", "admin123");

        ds.registerUser("desi", "desi123");
        ds.registerDriver("ivan", "ivan123");

        ds.printInfo();

        ds.login("desi", "desi123");
        ds.addAddress(new Address("Bulgaria", "Sofia", "Slaveykov", ds.currentUser.getId()));
        System.out.println("Current user: " + ds.currentUser.getUsername());




        //write users to csv file

        try {
            FileWriter writer = new FileWriter("users2.csv");
            for (User u : ds.users) {
                writer.write(u.getId() + "," + u.getUsername() + "," + u.getPassword() + "," + u.getRole() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}