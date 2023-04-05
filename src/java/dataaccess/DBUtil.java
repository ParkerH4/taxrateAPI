package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * DBUtil class that contains a singleton EntityManagerFactory for EntityManager creation, which uses and updates persistence entities.
 * 
 */
public class DBUtil {
    
    private static final EntityManagerFactory EMF
            = Persistence.createEntityManagerFactory("taxRatePU2");

    /**
     * 
     * @return EntityManagerFactory for use throughout the application.
     */
    public static EntityManagerFactory getEmFactory() {
        return EMF;
    }

    
}
