package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;
/**
 *
 * @author William Lau
 */
public class UserService {
        
    private UserDB userDB = new UserDB();
    public List<User> getAll() throws Exception {
            return userDB.getAll();
        }
        
    public User get(String username) throws Exception {
            
        User user = userDB.get(username);
            return user;
            
        }

    public User login(String username, String password) throws Exception {

        User user = userDB.get(username);
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public void insert(int userId, String username, String password, int roleId) throws Exception {
        
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleId);
        
        User user = new User(userId, username, password);
        user.setRoleId(role);
        userDB.insert(user);
    }

    public void update(int userId, String username, String password, int roleId) throws Exception {
        
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleId);
        
        User user = userDB.get(username);
        
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setRoleId(role);
        
        userDB.update(user);
   
    }
    
    public void delete(String username) throws Exception {
        
        User user = userDB.get(username);
        userDB.delete(user);
        
    }
}

