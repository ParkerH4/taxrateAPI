/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import models.Canadalocation;

/**
 *
 * @author Kyle Helmer
 * Entity manager 
 */
public class LocationDB {

    public LocationDB() {
        
    }
    
    
    
    
    public List<Canadalocation> getAllCan() throws Exception{
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
                try{
                    
                }finally{
                    em.close();
                }
        
        return null;
    }
    
    public Canadalocation getCan(){
        return null;
    }
    
    public void insertCan(){
        
    }
    
    public void updateCan(){
        
    }
    
    public void deleteCan(){
        
    }
    
}
