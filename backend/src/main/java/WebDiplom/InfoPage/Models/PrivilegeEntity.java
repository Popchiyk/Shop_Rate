package WebDiplom.InfoPage.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class    PrivilegeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<RoleEntity> roles;

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

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }

    public PrivilegeEntity(String name, Collection<RoleEntity> roles) {
        this.name = name;
        this.roles = roles;
    }
    public  PrivilegeEntity(String name){
        this.name = name;
    }
    public PrivilegeEntity(){}
}
