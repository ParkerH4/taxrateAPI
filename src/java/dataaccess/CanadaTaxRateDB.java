package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Location;
import models.CanadaTaxRate;

/**
 * CanadaTaxRateDB class supports CRUD functionality on the CanadaTaxRate database table.
 * 
 */
public class CanadaTaxRateDB {

    /**
     * 
     * @param locationCode location code string that represents a Canada Postal Code
     * @return CanadaTaxRate object retrieved using the locationCode.
     */
    public CanadaTaxRate getCan(String locationCode) {

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

    /**
     * 
     * @return a List of CanadaTaxRate objects.
     */
    public List<CanadaTaxRate> getAllCan() {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {

            List<CanadaTaxRate> taxRates = em.createNamedQuery("CanadaTaxRate.findAll", CanadaTaxRate.class).getResultList();
            return taxRates;

        } finally {
            em.close();
        }
    }

    /**
     * 
     * @param canTaxRate A CanadaTaxRate object that will be inserted into the DB.
     */
    public void insertCan(CanadaTaxRate canTaxRate) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {

            LocationDB locDB = new LocationDB();
            Location loc = locDB.getLoc(canTaxRate.getLocation().getLocationCode());

            tran.begin();
            em.merge(loc);

            em.persist(canTaxRate);

            tran.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            tran.rollback();
        } finally {
            em.close();
        }
    }

    /**
     * 
     * @param canTaxRate The CanadaTaxRate that will be deleted from the DB.
     */
    public void deleteCan(CanadaTaxRate canTaxRate) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {

            tran.begin();
            em.remove(em.merge(canTaxRate));
            tran.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            tran.rollback();
        } finally {
            em.close();
        }
    }
    
    /**
     * 
     * @param canTaxRate The CanadaTaxRate object that will be updated in the database.
     */
    public void updateCan(CanadaTaxRate canTaxRate) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {

            tran.begin();
            em.merge(canTaxRate);
            tran.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            tran.rollback();
        } finally {
            em.close();
        }
    }

}
