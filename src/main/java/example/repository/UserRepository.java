package example.repository;

import dbgateway.dao.DBRepository;
import example.entity.User;
import org.hibernate.Session;

public class UserRepository extends DBRepository<User, Long> {

    public UserRepository(Session session) {
        super(session);
    }
}
