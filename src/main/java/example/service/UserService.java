package example.service;

import dbgateway.exception.DBConnectionConfigException;
import example.entity.User;
import example.manager.UserManager;

public class UserService {

    private UserManager userManager;

    public UserService() throws DBConnectionConfigException {
        userManager = new UserManager();
    }

    public User saveUser(User user) {
        return userManager.saveUser(user);
    }
}
