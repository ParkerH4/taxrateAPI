/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import models.Role;
import dataaccess.RoleDB;
import java.util.List;

/**
 *
 * @author Kyle Helmer
 * 
 */
public class RoleService {
    
    
   private RoleDB roleDB = new RoleDB();

    public List<Role> getAll() throws Exception{
        
        return roleDB.getAll();
    }

    public Role get(int roleID) throws Exception{
        Role role = roleDB.get(roleID);
        return role;
    }
    
}
