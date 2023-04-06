package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Role is an entity class representing a role record in the role table. This
 * class provides mapping and persistence for the role data in the database and
 * includes associated properties, constructors, and methods for accessing and
 * manipulating the role data.
 */
@Entity
@Table(name = "role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
    , @NamedQuery(name = "Role.findByRoleId", query = "SELECT r FROM Role r WHERE r.roleId = :roleId")
    , @NamedQuery(name = "Role.findByRoleName", query = "SELECT r FROM Role r WHERE r.roleName = :roleName")})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "role_id")
    private Integer roleId;
    @Basic(optional = false)
    @Column(name = "role_name")
    private String roleName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId", fetch = FetchType.EAGER)
    private List<User> userList;

    /**
     * Initializes a new instance of the Role class with no arguments.
     */
    public Role() {
    }

    /**
     * Initializes a new instance of the Role class with the specified role ID.
     *
     * @param roleId The unique identifier for the role.
     */
    public Role(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * Initializes a new instance of the Role class with the specified role ID
     * and role name.
     *
     * @param roleId The unique identifier for the role.
     * @param roleName The name of the role.
     */
    public Role(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    /**
     * Retrieves the role ID.
     *
     * @return The unique identifier for the role.
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Sets the role ID.
     *
     * @param roleId The unique identifier for the role.
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * Retrieves the role name.
     *
     * @return The name of the role.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the role name.
     *
     * @param roleName The name of the role.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Retrieves the list of users associated with this role.
     *
     * @return A list of User objects associated with the role.
     */
    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Sets the list of users associated with this role.
     *
     * @param userList A list of User objects to be associated with the role.
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * Calculates the hash code for the Role object using the role ID.
     *
     * @return An integer representing the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    /**
     * Compares this Role object to the specified object for equality. The
     * comparison is based on the role ID.
     *
     * @param object The object to compare this Role with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    /**
     * Generates a String representation of the Role object.
     *
     * @return A String representation of the Role object.
     */
    @Override
    public String toString() {
        return "models.Role[ roleId=" + roleId + " ]";
    }

}
