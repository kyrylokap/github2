package org.example.Repositories.UserRepositories;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepositoryImpl implements IUserRepository{
    private List<User>users = new ArrayList<>();
    @Override
    public String getUsers(){
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger atomicInteger = new AtomicInteger(1);
        users.forEach(user -> {
            stringBuilder.append(atomicInteger.getAndIncrement()).append(". ").append(user.toString()).append("\n");
        });
        return stringBuilder.toString();
    }

    public UserRepositoryImpl() throws IOException {
        addUser(new User("kyrylo", DigestUtils.sha256Hex("qwerty"),"user"));
        addUser(new User("admin",DigestUtils.sha256Hex("admin"),"admin"));
        load();
    }
    @Override
    public void addUser(User user){
        users.add(user);
    }
    @Override
    public void load() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("users.csv"));
        String line;
        while ((line = br.readLine()) !=  null){
            String[] v = line.split(",");
            users.add(new User(v[0],v[1],v[2]));
        }
    }
    @Override
    public boolean register(User user){
        for(User us:users){
            if(user.getUsername().equals(us.getUsername())){
                return false;
            }
        }
        System.out.print("Successfully registered! ");
        System.out.println(user.toString());
        return true;
    }

    @Override
    public boolean login(User user){
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        for(User user1:users){
            if (user1.getUsername().equals(user.getUsername())){
                System.out.println("Znaleziono usera!");
                if(!user1.getPassword().equals(user.getPassword())){
                    System.out.println("Niepoprawne haslo!");
                }else{
                    if(user1.getUsername().equals("admin")){
                        System.out.println("Udalo sie zalogowac sie adminu!");
                        user.setRole("admin");
                    }
                    else {
                        user.setRole("user");
                        System.out.println("Udalo sie zalogowac!");
                    }
                    return true;
                }
            }

        }
        return false;
    }

}
