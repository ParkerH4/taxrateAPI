package dataaccess;

import models.Role;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * The LocationDB class is used to support CRUD functionality on the role table
 * in the taxrate database.
 *
 */
public class RoleDB {

    /**
     * Retrieves all Role objects from the database.
     *
     * @return A list of all Role objects stored in the database.
     */
    public List<Role> getAll() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Role> roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
            return roles;
        } finally {
            em.close();
        }
    }

    /**
     * Retrieves a Role object from the database using the provided roleId.
     *
     * @param roleId The unique identifier of the Role to be retrieved.
     * @return The Role object associated with the given roleId.
     */
    public Role get(int roleId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Role role = em.find(Role.class, roleId);
            return role;
        } finally {
            em.close();
        }

    }

}
