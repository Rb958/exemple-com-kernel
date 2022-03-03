package dbgateway.config.repository;

import dbgateway.config.entity.User;
import dbgateway.dao.DBRepository;
import org.hibernate.Session;

public class UserRepository extends DBRepository<User, Long> {
    public UserRepository(Session session) {
        super(session);
    }
}
