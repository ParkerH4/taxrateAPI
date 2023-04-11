package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Location;

/**
 * LocationDB class is used to support CRUD functionality on the location table
 * in the taxrate database.
 *
 */
public class LocationDB {

    public LocationDB() {
    }

    /**
     *
     * @return A List of all Location objects from the database.
     */
    public List<Location> getAll() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Location> locations = em.createNamedQuery("Location.findAll", Location.class).getResultList();
            return locations;

        } finally {
            em.close();
        }

    }

    /**
     *
     * @param locationCode The string that represents a US ZipCode or Canada
     * Postal Code
     * @return A location object retrieved from the database.
     */
    public Location getLoc(String locationCode) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Location loc = em.find(Location.class, locationCode);
            return loc;
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param region The internationally approved alpha code, which is a
     * representation of a US State or Canada Province.
     * @return the List of all location objects associated with the alpha code
     */
    public List<Location> getAllRegion(String region) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Query queryCanLocByregion = em.createNamedQuery("Location.findByRegion");
            queryCanLocByregion.setParameter("region", region);
            List<Location> locations = queryCanLocByregion.getResultList();
            return locations;
        } finally {
            em.close();
        }

    }

    /**
     *
     * @param loc Location object that will be inserted into the Location table
     * in the taxratedb database
     */
    public void insertLoc(Location loc) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {

            trans.begin();
            em.persist(loc);
            trans.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            trans.rollback();
        } finally {
            em.close();
        }

    }

    /**
     *
     * @param loc Location object that will be updated in the Location table of
     * the taxratedb database.
     */
    public void updateLoc(Location loc) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(em.merge(loc));
            trans.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            trans.rollback();
        } finally {
            em.close();
        }

    }

    /**
     *
     * @param loc Location object that will be removed from the Location table
     * of the taxratedb database.
     */
    public void deleteLoc(Location loc) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(loc));
            trans.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            trans.rollback();
            em.close();
        }finally {
            em.close();
        }

    }

}
