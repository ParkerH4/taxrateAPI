package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.User;
import models.Role;

/**
 *
 * @author William Lau
 */
public class UserDB {

    public User get(String username) throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {

            Query queryGetUser = em.createNamedQuery("User.findByUsername");
            queryGetUser.setParameter("username", username);
            User user = (User) queryGetUser.getSingleResult();
            return user;

        } finally {
            em.close();
        }
    }

    public List<User> getAll() throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {

            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;

        } finally {
            em.close();
        }
    }

    public void insert(User user) throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {

            Role role = user.getRoleId();
            role.getUserList().add(user);

            tran.begin();
            em.persist(user);
            em.merge(role);
            tran.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            tran.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(User user) throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {

            Role role = user.getRoleId();
            role.getUserList().remove(user);

            tran.begin();
            em.remove(em.merge(user));
            em.merge(role);
            tran.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            tran.rollback();
        } finally {
            em.close();
        }
    }

    public void update(User user) throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {

            tran.begin();
            em.merge(user);
            tran.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            tran.rollback();
        } finally {
            em.close();
        }
    }

}
