/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.Location;

/**
 *
 * @author Kyle Helmer
 */
public class LocationDB {

    public LocationDB() {

    }

    //Canada related methods 
    public List<Location> getAll() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Location> locations = em.createNamedQuery("Location.findAll", Location.class).getResultList();
            return locations;

        } finally {
            em.close();
        }

    }

    public Location getLoc(String locationCode) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Location loc = em.find(Location.class, locationCode);
            return loc;
        } finally {
            em.close();
        }
    }

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

    public void updateLoc(Location loc) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(loc);
            trans.commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            trans.rollback();
        } finally {
            em.close();
        }

    }

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
        }

    }

}
