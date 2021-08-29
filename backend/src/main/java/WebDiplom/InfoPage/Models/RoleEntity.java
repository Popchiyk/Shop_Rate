package WebDiplom.InfoPage.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Collection<UserEntity> users;
    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<PrivilegeEntity> privileges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserEntity> users) {
        this.users = users;
    }

    public RoleEntity(String name, Collection<UserEntity> users) {
        this.name = name;
        this.users = users;
    }

    public Collection<PrivilegeEntity> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<PrivilegeEntity> privileges) {
        this.privileges = privileges;
    }
    public RoleEntity(String name){
        this.name = name;
    }

    public RoleEntity() {
    }
}
