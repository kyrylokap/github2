package org.example.Services;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.models.User;
import org.example.Repositories.UserRepositories.UserRepositoryImpl;
import java.util.Scanner;

public class UserService{
    private UserRepositoryImpl userRepositoryImpl;
    private AuthService authService;

    public UserService(){
        userRepositoryImpl = new UserRepositoryImpl();
        authService = new AuthService(userRepositoryImpl);
    }

    public String getUsers(){
        return userRepositoryImpl.getUsers();
    }
    public boolean login(User user,Scanner scanner){
        return authService.isLoginSuccess(user,scanner);
    }

    public boolean register(Scanner scanner,User user){
        return authService.isRegisterSuccess(scanner,user);
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
              if (register(scanner,user))break;
            }
        }
    }

}
