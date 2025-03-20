package org.example.Services;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.Repositories.UserRepositories.User;
import org.example.Repositories.UserRepositories.UserRepositoryImpl;

import java.io.IOException;
import java.util.Scanner;

public class UserService{
    private UserRepositoryImpl userRepositoryImpl;
    private Authentication authentication;

    public UserService() throws IOException{
        userRepositoryImpl = new UserRepositoryImpl();
        authentication = new Authentication(userRepositoryImpl);
    }

    public String getUsers(){
        return userRepositoryImpl.getUsers();
    }
    public boolean login(User user,Scanner scanner){
        System.out.println("Podaj username:");
        user.setUsername(scanner.nextLine());
        System.out.println("Podaj haslo:");
        user.setPassword(scanner.nextLine());
        return authentication.isLoginSuccess(user);

    }

    public boolean register(Scanner scanner){
        String username, haslo;
        System.out.println("Podaj username do rejestracji: ");
        username = scanner.nextLine();
        System.out.println("Podaj haslo do rejestracji: ");
        haslo = scanner.nextLine();
        return authentication.isRegisterSuccess(new User(username, DigestUtils.sha256Hex(haslo),"user"));
    }

    public void logOrReg(User user,Scanner scanner){
        int choose;
        System.out.println("1.Login");
        System.out.println("2.Register");
        choose = scanner.nextInt();
        scanner.nextLine();
        if(choose == 1){
            while(true){
                if (login(user,scanner))break;
            }
        }else {
            while(true){
              if (register(scanner))break;
            }
        }
    }

}
