package org.example.Repositories.UserRepositories;

import com.google.gson.reflect.TypeToken;
import org.example.JsonFileStorage;
import org.example.models.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepositoryImpl implements IUserRepository{
    JsonFileStorage<User> jsonFileStorage = new JsonFileStorage<>("users.json", new TypeToken<List<User>>(){}.getType());
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

    public UserRepositoryImpl(){
        load();
    }
    @Override
    public void addUser(User user){
        users.add(user);
        jsonFileStorage.save(users);
    }
    @Override
    public void load(){
        users = jsonFileStorage.load();
    }
    @Override
    public boolean register(User user){
        for(User us:users){
            if(user.getLogin().equals(us.getLogin())){
                return false;
            }
        }
        user.setId(BCrypt.hashpw(String.valueOf(users.size()), BCrypt.gensalt()));
        users.add(user);
        jsonFileStorage.save(users);
        System.out.print("Rejestracja jest udana! ");

        return true;
    }

    @Override
    public boolean login(User user){
        System.out.println(user.getPassword());
        for(User user1:users){
            if (user1.getLogin().equals(user.getLogin())){
                System.out.println("Znaleziono usera!");
                if(!BCrypt.checkpw(user.getPassword(), user1.getPassword())){
                    System.out.println("Niepoprawne haslo!");
                }else{
                    if(user1.getRole().equals("ADMIN")){
                        System.out.println("Udalo sie zalogowac sie adminu!");
                        user.setRole("admin");
                    }
                    else {
                        user.setRole("USER");
                        System.out.println("Udalo sie zalogowac!");
                    }
                    return true;
                }
            }

        }
        return false;
    }

}
