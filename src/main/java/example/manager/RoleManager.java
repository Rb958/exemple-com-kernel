package example.manager;

import dbgateway.exception.DBConnectionConfigException;
import dbgateway.manager.AbstractManager;
import example.entity.EntityUtility;
import example.entity.Role;
import example.repository.RoleRepository;

public class RoleManager extends AbstractManager {

    private final RoleRepository roleRepository;

    public RoleManager() throws DBConnectionConfigException {
        super(EntityUtility.getDataBaseName(), EntityUtility.getClasses());
        roleRepository = new RoleRepository(getSession());
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role findOneByLabel(String label){
        return roleRepository.findOneByLabel(label);
    }

    public void deleteById(long id){
        roleRepository.deleteById(id);
    }

    public void delete(Role role){
        roleRepository.delete(role);
    }
}
