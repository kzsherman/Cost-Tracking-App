package vip.almaty.costtrackingapp.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name="groups")
public class Group
{
    private Long id;
    private String name;
    private User user;
    private Set<Category> categories = new TreeSet<>();

    @Id @GeneratedValue
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    @ManyToOne
    public User getUser()
    {
        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }

    @OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="group")
    public Set<Category> getCategories()
    {
        return categories;
    }
    public void setCategories(Set<Category> categories)
    {
        this.categories = categories;
    }

}
