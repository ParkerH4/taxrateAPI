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
import models.Canadalocation;

/**
 *
 * @author Kyle Helmer Entity manager
 */
public class LocationDB {

    public LocationDB() {

    }

    public List<Canadalocation> getAllCan() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Canadalocation> canLocations = em.createNamedQuery("Canadalocation.findAll", Canadalocation.class).getResultList();
            return canLocations;

        } finally {
            em.close();
        }

    }

    public Canadalocation getCan(String locationCode) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Canadalocation can = em.find(Canadalocation.class, locationCode);
            return can;
        } finally {
            em.close();
        }
    }

    public List<Canadalocation> getAllCanRegion(String region) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            Query queryCanLocByregion = em.createNamedQuery("Canadalocation.findByRegion");
            queryCanLocByregion.setParameter("region", region);
            List<Canadalocation> canLocations = queryCanLocByregion.getResultList();
            return canLocations;
        } finally {
            em.close();
        }

    }

    public void insertCan(Canadalocation canLoc) {

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

    public void updateCan(Canadalocation canLoc) {

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

    public void deleteCan(Canadalocation canLoc) {

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
