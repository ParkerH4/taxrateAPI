package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Canadalocation;
import models.Canadataxrate;

/**
 *
 * @author William Lau
 */
public class TaxRateDB {
    
    public Canadataxrate getCan(String locationCode) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Query queryCanLocCode = em.createNamedQuery("Canadalocation.findByLocationCode");
            queryCanLocCode.setParameter("locationCode", locationCode);
            Canadalocation canLoc = (Canadalocation) queryCanLocCode.getSingleResult();
            
            Canadataxrate canTaxRate = canLoc.getCanadataxrateList().get(0);
            
            return canTaxRate;
            
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
    
    public void insertCan(Canadataxrate canTaxRate) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
         
            tran.begin();
            em.persist(canTaxRate);
            tran.commit();
            
        } catch (Exception e) {
            tran.rollback();
        } finally {
            em.close();
        }
    }
    
    public void deleteCan(Canadataxrate canTaxRate) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();
        
        try {
           
            tran.begin();
            em.remove(em.merge(canTaxRate));
            tran.commit();
            
        } catch (Exception e) {
            tran.rollback();
        } finally {
            em.close();
        }
    }
    
    public void updateCan(Canadataxrate canTaxRate) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            
            tran.begin();
            em.merge(canTaxRate);
            tran.commit();
            
        } catch (Exception e) {
            tran.rollback();
        } finally {
            em.close();
        }
    }
    
}
    
