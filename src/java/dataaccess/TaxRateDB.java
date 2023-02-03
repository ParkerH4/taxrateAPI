package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Canadataxrate;

/**
 *
 * @author William Lau
 */
public class TaxRateDB {
    
    public Canadataxrate getCan(String locationCode) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            
            Canadataxrate taxRate = em.find(Canadataxrate.class, locationCode);
            return taxRate;
            
        } finally {
            em.close();
        }
    }
    
    public List<Canadataxrate> getAllCan() throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            
            List<Canadataxrate> canTaxRates = em.createNamedQuery("Canadataxrate.findAll", Canadataxrate.class).getResultList();
            return canTaxRates;
            
        } finally {
            em.close();
        }
    }
    
    public void insertCan(Canadataxrate taxRate) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
         
            tran.begin();
            em.persist(taxRate);
            tran.commit();
            
        } catch (Exception e) {
            tran.rollback();
        } finally {
            em.close();
        }
    }
    
    public void deleteCan(Canadataxrate taxRate) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        
        try {
           
            tran.begin();
            em.remove(em.merge(taxRate));
            tran.commit();
            
        } catch (Exception e) {
            tran.rollback();
        } finally {
            em.close();
        }
    }
    
    public void updateCan(Canadataxrate taxRate) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            
            tran.begin();
            em.merge(taxRate);
            tran.commit();
            
        } catch (Exception e) {
            tran.rollback();
        } finally {
            em.close();
        }
    }
    
}
    
