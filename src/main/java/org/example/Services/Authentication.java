package org.example.Services;

import org.example.Repositories.UserRepositories.User;
import org.example.Repositories.UserRepositories.UserRepositoryImpl;

public class Authentication {
    private UserRepositoryImpl userRepositoryImpl;
    public Authentication(UserRepositoryImpl userRepositoryImpl){
        this.userRepositoryImpl = userRepositoryImpl;
    }

    public boolean isLoginSuccess(User user){
        return userRepositoryImpl.login(user);
    }
    public boolean isRegisterSuccess(User user){
        return userRepositoryImpl.register(user);
    }
}
