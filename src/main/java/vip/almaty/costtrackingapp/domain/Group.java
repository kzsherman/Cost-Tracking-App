package vip.almaty.costtrackingapp.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name="groups")
public class Group implements Comparable <Group>
{
    private Long id;
    private String name;
    private Budget budget;
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
    public Budget getBudget()
    {
        return budget;
    }
    public void setBudget(Budget budget)
    {
        this.budget = budget;
    }

    @OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="group")
    @JsonIgnore
    public Set<Category> getCategories()
    {
        return categories;
    }
    public void setCategories(Set<Category> categories)
    {
        this.categories = categories;
    }

    @Override
    public int compareTo(Group o) {


        int compareTo = 0;
        if(this.getId() != null && o.getId() != null)
        compareTo = o.getId().compareTo((o.getId()));

        return compareTo;
    }
}
