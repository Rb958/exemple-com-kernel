package dbgateway.config.repository;

import dbgateway.config.entity.Role;
import dbgateway.dao.DBRepository;
import org.hibernate.Session;

public class RoleRepository extends DBRepository<Role, Long> {
    public RoleRepository(Session session) {
        super(session);
    }
}
