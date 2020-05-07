package service.impl;

import model.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
private UserRepository userRepository=new UserRepositoryImpl();
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
