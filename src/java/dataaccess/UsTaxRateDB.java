package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Location;
import models.UsTaxRate;

/**
 *
 * @author William Lau
 */
public class UsTaxRateDB {

    public UsTaxRate getUs(String locationCode) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Query queryCanLocCode = em.createNamedQuery("Location.findByLocationCode");
            queryCanLocCode.setParameter("locationCode", locationCode);
            Location Loc = (Location) queryCanLocCode.getSingleResult();

            UsTaxRate usTaxRate = Loc.getUsTaxRateList().get(0);

            return usTaxRate;

        } finally {
            em.close();
        }
    }

    public List<UsTaxRate> getAllUs() {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {

            List<UsTaxRate> taxRates = em.createNamedQuery("UsTaxRate.findAll", UsTaxRate.class).getResultList();
            return taxRates;

        } finally {
            em.close();
        }
    }

    public void insertUs(UsTaxRate usTaxRate) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {

            LocationDB locDB = new LocationDB();
            Location loc = locDB.getLoc(usTaxRate.getLocation().getLocationCode());

            tran.begin();
            em.merge(loc);
            em.persist(usTaxRate);
            tran.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            tran.rollback();
        } finally {
            em.close();
        }
    }

    public void deleteUs(UsTaxRate usTaxRate) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {
            Location loc = usTaxRate.getLocation();
            tran.begin();
            loc.getUsTaxRateList().clear();
            em.remove(em.merge(usTaxRate));
            em.merge(loc);
            tran.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            tran.rollback();
        } finally {
            em.close();
        }
    }

    public void updateUs(UsTaxRate usTaxRate) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {

            tran.begin();
            em.merge(usTaxRate);
            tran.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            tran.rollback();
        } finally {
            em.close();
        }
    }

}
