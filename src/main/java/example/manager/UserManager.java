package example.manager;

import dbgateway.exception.DBConnectionConfigException;
import dbgateway.manager.AbstractManager;
import example.entity.EntityUtility;
import example.entity.User;
import example.repository.UserRepository;

import java.util.List;

public class UserManager extends AbstractManager {

    private UserRepository userRepository;

    public UserManager() throws DBConnectionConfigException {
        super(EntityUtility.getDataBaseName(), EntityUtility.getClasses());
        userRepository = new UserRepository(getSession());
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
