package net.guard.passer.service;

import net.guard.passer.entity.Role;
import net.guard.passer.entity.User;


import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUser(int id);

    public void deleteUser(int id);

    public void saverUserWithDefaultRole(User user);

    public List<Role> getRoles();
}
