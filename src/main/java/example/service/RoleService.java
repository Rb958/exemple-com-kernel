package example.service;

import dbgateway.exception.DBConnectionConfigException;
import example.entity.EntityUtility;
import example.entity.Role;
import example.manager.RoleManager;

public class RoleService {

    private RoleManager roleManager;

    public RoleService() throws DBConnectionConfigException {
        roleManager = new RoleManager();
    }

    public Role saveRole(Role role){
        return roleManager.save(role);
    }
}
