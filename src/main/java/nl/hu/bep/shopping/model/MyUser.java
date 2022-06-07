package nl.hu.bep.shopping.model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class MyUser implements Principal {

    private String userName;
    private String password;
    private String role;

    private static List<MyUser> allUsers = new ArrayList<>();
    public MyUser(String userName, String password, String role){
        this.userName = userName;
        this.password = password;
        this.role = role;
        allUsers.add(this);

    }
    public static String validateLogin(String userName, String password) {
        for(MyUser user : allUsers){
            if (user.getName().equals(userName) && user.getPassword().equals(password)){
                return user.getRole();
            }
        }
        return null;
    }

    public static MyUser getUserByName(String user) {
        for(MyUser username : allUsers){
            if (username.getName().equals(user)){
                return username;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}
