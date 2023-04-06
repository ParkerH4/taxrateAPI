package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Location;
import models.UsTaxRate;

/**
 * The UsTaxRateDB class is used to support CRUD functionality on the UsTaxRate
 * table in the database.
 */
public class UsTaxRateDB {

    /**
     * Retrieves the UsTaxRate associated with the given locationCode.
     *
     * @param locationCode The string representing a US ZipCode or Canada Postal
     * Code.
     * @return The UsTaxRate object associated with the provided locationCode,
     * or null if not found.
     */
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

    /**
     * Retrieves all UsTaxRate objects from the database.
     *
     * @return A list of all UsTaxRate objects stored in the database.
     */
    public List<UsTaxRate> getAllUs() {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {

            List<UsTaxRate> taxRates = em.createNamedQuery("UsTaxRate.findAll", UsTaxRate.class).getResultList();
            return taxRates;

        } finally {
            em.close();
        }
    }

    /**
     * Inserts a new UsTaxRate object into the database.
     *
     * @param usTaxRate The UsTaxRate object to be inserted.
     */
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

    /**
     * Deletes the given UsTaxRate object from the database.
     *
     * @param usTaxRate The UsTaxRate object to be deleted.
     */
    public void deleteUs(UsTaxRate usTaxRate) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = em.getTransaction();

        try {

            tran.begin();
            em.remove(em.merge(usTaxRate));
            tran.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            tran.rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Updates the given UsTaxRate object in the database.
     *
     * @param usTaxRate The UsTaxRate object containing the updated information.
     */
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
