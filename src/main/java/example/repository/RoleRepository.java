package example.repository;

import dbgateway.dao.DBRepository;
import example.entity.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RoleRepository extends DBRepository<Role, Long> {
    public RoleRepository(Session session) {
        super(session);
    }

    public Role findOneByLabel(String label){
        Query<Role> query = session.createQuery("SELECT r FROM Role r WHERE r.label = :label")
                .setParameter("label", label);
        return query.getSingleResult();
    }
}
