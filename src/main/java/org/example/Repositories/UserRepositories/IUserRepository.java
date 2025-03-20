package org.example.Repositories.UserRepositories;

import java.io.IOException;

public interface IUserRepository {
    public String getUsers();
    public void addUser(User user);
    public void load() throws IOException;
    public boolean register(User user);
    public boolean login(User user);
}
