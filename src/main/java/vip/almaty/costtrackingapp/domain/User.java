package vip.almaty.costtrackingapp.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name="users")
public class User
{
    private Long id;
    private String username;
    private String password;
    private Set<Group> groups = new TreeSet<>();

    @Id
    @GeneratedValue
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")
    public Set<Group> getGroups()
    {
        return groups;
    }
    public void setGroups(Set<Group> groups)
    {
        this.groups = groups;
    }
}
