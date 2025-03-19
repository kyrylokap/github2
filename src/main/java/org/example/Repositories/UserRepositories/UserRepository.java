package org.example.Repositories.UserRepositories;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User>users = new ArrayList<>();
    public UserRepository(){
        addUser(new User("kyrylo", DigestUtils.sha256Hex("qwerty"),"user"));
        addUser(new User("admin",DigestUtils.sha256Hex("admin"),"admin"));
    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean login(User user){
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        for(User user1:users){
            if (user1.getUsername().equals(user.getUsername())){
                System.out.println("Znaleziono usera!");
                if(!user1.getPassword().equals(user.getPassword())){
                    System.out.println("Niepoprawne haslo!");
                }else{
                    if(user1.getUsername().equals(DigestUtils.sha256Hex("admin")) )
                        System.out.println("Udalo sie zalogowac sie adminu!");
                    else System.out.println("Udalo sie zalogowac!");
                    return true;
                }
            }

        }
        return false;
    }

}
