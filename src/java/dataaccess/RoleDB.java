package dataaccess;

import models.Role;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Kyle Helmer Entity manager
 */
public class RoleDB {

    public List<Role> getAll() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Role> roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
            return roles;
        } finally {
            em.close();
        }

    }

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
