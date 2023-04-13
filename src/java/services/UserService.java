package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 * UserService is a class that provides services related to User management,
 * including retrieving, creating, updating, and deleting User records.
 */
public class UserService {

    private UserDB userDB = new UserDB();

    /**
     * Retrieves all User records.
     *
     * @return A list of all User records.
     * @throws Exception if an error occurs during the database operation.
     */
    public List<User> getAll() throws Exception {
        return userDB.getAll();
    }

    /**
     * Retrieves a User record by the specified username.
     *
     * @param username The username of the user to be retrieved.
     * @return The User record matching the specified username.
     * @throws Exception if an error occurs during the database operation.
     */
    public User get(String username) throws Exception {
        User user = userDB.get(username);
        return user;
    }

    /**
     * Authenticates a user by checking the provided username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The authenticated User record if the credentials are valid, null
     * otherwise.
     * @throws Exception if an error occurs during the database operation.
     */
    public User login(String username, String password) throws Exception {
        User user = userDB.get(username);
        if (user!=null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * Inserts a new User record with the specified parameters.
     *
     * @param userId The unique identifier for the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param roleId The role identifier associated with the user.
     * @throws Exception if an error occurs during the database operation.
     */
    public void insert(int userId, String username, String password, int roleId) throws Exception {

        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleId);
        User user = new User(userId, username, password);
        user.setRoleId(role);
        
        userDB.insert(user);
    }

    /**
     * Updates an existing User record with the specified parameters.
     *
     * @param userId The unique identifier for the user.
     * @param username The updated username of the user.
     * @param password The updated password of the user.
     * @param roleId The updated role identifier associated with the user.
     * @throws Exception if an error occurs during the database operation.
     */
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

    /**
     * Deletes a User record by the specified username.
     *
     * @param username The username of the user to be deleted.
     * @throws Exception if an error occurs during the database operation.
     */
    public void delete(String username) throws Exception {
        User user = userDB.get(username);
        
        userDB.delete(user);
    }
}
