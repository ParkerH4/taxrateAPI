package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
        
            User user = em.find(User.class, username);
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
            
        } catch (Exception e) {
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
            
        } catch (Exception e) {
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
            
        }catch (Exception e) {
            tran.rollback();
        }finally {
            em.close();
        }
    }
    
}
