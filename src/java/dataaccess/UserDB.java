package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import models.User;
import models.Role;

/**
 * The UserDB class is used to support CRUD functionality on the User table in
 * the database.
 *
 */
public class UserDB {

    /**
     * Retrieves the User object associated with the given username.
     *
     * @param username The string representing the username of the User.
     * @return The User object associated with the provided username, or null if
     * not found.
     * @throws Exception If any exception occurs during the database operation.
     */
    public User get(String username) throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {

            Query queryGetUser = em.createNamedQuery("User.findByUsername");
            queryGetUser.setParameter("username", username);
            User user = (User) queryGetUser.getSingleResult();
            return user;

        }catch(NoResultException e){
            
            return null;
        }finally {
            em.close();
        }
    }

    /**
     * Retrieves all User objects from the database.
     *
     * @return A list of all User objects stored in the database.
     * @throws Exception If any exception occurs during the database operation.
     */
    public List<User> getAll() throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {

            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;

        } finally {
            em.close();
        }
    }

    /**
     * Inserts a new User object into the database.
     *
     * @param user The User object to be inserted.
     * @throws Exception If any exception occurs during the database operation.
     */
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

    /**
     * Deletes the given User object from the database.
     *
     * @param user The User object to be deleted.
     * @throws Exception If any exception occurs during the database operation.
     */
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

    /**
     * Updates the given User object in the database.
     *
     * @param user The User object containing the updated information.
     * @throws Exception If any exception occurs during the database operation.
     */
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
