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
    
    public List<Location> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Location> canLocations = em.createNamedQuery("Location.findAll", Location.class).getResultList();
            return canLocations;

        } finally {
            em.close();
        }

    }

    public Location getLoc(String locationCode) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Location can = em.find(Location.class, locationCode);
            return can;
        } finally {
            em.close();
        }
    }

    public List<Location> getAllRegion(String region) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Query queryCanLocByregion = em.createNamedQuery("Location.findByRegion");
            queryCanLocByregion.setParameter("region", region);
            List<Location> canLocations = queryCanLocByregion.getResultList();
            return canLocations;
        } finally {
            em.close();
        }

    }

    public void insertLoc(Location canLoc) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {

            trans.begin();
            em.persist(canLoc);
            trans.commit();

        } finally {
            em.close();
        }

    }

    public void updateLoc(Location canLoc) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(canLoc);
            trans.commit();
        } finally {
            em.close();
        }

    }

    public void deleteLoc(Location canLoc) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.remove(em.merge(canLoc));
            trans.commit();
        } finally {
            em.close();
        }

    }
    
       

}
