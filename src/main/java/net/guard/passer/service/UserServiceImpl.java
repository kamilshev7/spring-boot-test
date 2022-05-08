package net.guard.passer.service;

import net.guard.passer.entity.Role;
import net.guard.passer.entity.User;
import net.guard.passer.repository.RoleRepository;
import net.guard.passer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saverUserWithDefaultRole(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        Role roleUser = roleRepository.findByName("USER");
        user.addRole(roleUser);

        userRepository.save(user);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by("id"));
    }

    @Override
    public void saveUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public User getUser(int id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            user = optional.get();
        }
        return user;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
