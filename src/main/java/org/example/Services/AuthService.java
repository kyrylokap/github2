package org.example.Services;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.models.User;
import org.example.Repositories.UserRepositories.UserRepositoryImpl;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Scanner;

public class AuthService {
    private UserRepositoryImpl userRepositoryImpl;
    public AuthService(UserRepositoryImpl userRepositoryImpl){
        this.userRepositoryImpl = userRepositoryImpl;
    }

    public boolean isLoginSuccess(User user,Scanner scanner){
        System.out.println("Podaj username:");
        user.setLogin(scanner.nextLine());
        System.out.println("Podaj haslo:");
        user.setPassword(scanner.nextLine());
        return userRepositoryImpl.login(user);
    }
    public boolean isRegisterSuccess(Scanner scanner,User user){
        String username, haslo;
        System.out.println("Podaj username do rejestracji: ");
        username = scanner.nextLine();
        System.out.println("Podaj haslo do rejestracji: ");
        haslo = scanner.nextLine();
        user.setLogin(username);
        user.setPassword(BCrypt.hashpw(haslo, BCrypt.gensalt()));
        user.setRole("USER");
        return userRepositoryImpl.register(user);
    }

}
