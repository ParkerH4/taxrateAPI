package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Location;
import models.CanadaTaxRate;

/**
 *
 * @author William Lau
 */
public class CanadaTaxRateDB {

    public CanadaTaxRate getCan(String locationCode) throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Query queryCanLocCode = em.createNamedQuery("Location.findByLocationCode");
            queryCanLocCode.setParameter("locationCode", locationCode);
            Location Loc = (Location) queryCanLocCode.getSingleResult();

            CanadaTaxRate canTaxRate = Loc.getCanadaTaxRateList().get(0);
            return canTaxRate;

        } finally {
            em.close();
        }
    }

    public List<CanadaTaxRate> getAllCan() throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {

            List<CanadaTaxRate> taxRates = em.createNamedQuery("CanadaTaxRate.findAll", CanadaTaxRate.class).getResultList();
            return taxRates;

        } finally {
            em.close();
        }
    }

    public void insertCan(CanadaTaxRate canTaxRate) throws Exception {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            
            LocationDB locDB = new LocationDB();
            Location loc = locDB.getLoc(canTaxRate.getLocation().getLocationCode());

            tran.begin();
            em.persist(canTaxRate);
            em.merge(loc);
            tran.commit();

        } catch (Exception e) {
            tran.rollback();
        } finally {
            em.close();
        }
    }

    public void deleteCan(CanadaTaxRate canTaxRate) throws Exception {

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

    public void updateCan(CanadaTaxRate canTaxRate) throws Exception {

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
