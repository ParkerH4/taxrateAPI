package services;

import models.Role;
import dataaccess.RoleDB;
import java.util.List;

/**
 * RoleService is a class responsible for managing role data and providing
 * methods to interact with the RoleDB database class.
 */
public class RoleService {

    /**
     * The instance of RoleDB used for accessing and modifying role data.
     */
    private RoleDB roleDB = new RoleDB();

    /**
     * Retrieves a list of all roles.
     *
     * @return A list of Role objects representing all roles.
     * @throws Exception if an error occurs during the database query.
     */
    public List<Role> getAll() throws Exception {
        return roleDB.getAll();
    }

    /**
     * Retrieves a role by its role ID.
     *
     * @param roleID The role ID to search for.
     * @return A Role object representing the role with the specified role ID.
     * @throws Exception if an error occurs during the database query.
     */
    public Role get(int roleID) throws Exception {
        Role role = roleDB.get(roleID);
        return role;
    }
}
