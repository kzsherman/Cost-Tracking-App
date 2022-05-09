package vip.almaty.costtrackingapp.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Category
{
    private Long id;
    private BigDecimal budget;
    private String name;
    private Group group;
    private Set<Transaction> transactions = new TreeSet<>();

    @Id @GeneratedValue
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public BigDecimal getBudget()
    {
        return budget;
    }
    public void setBudget(BigDecimal budget)
    {
        this.budget = budget;
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
    public Group getGroup()
    {
        return group;
    }
    public void setGroup(Group group)
    {
        this.group = group;
    }
    @OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="category")
    public Set<Transaction> getTransactions()
    {
        return transactions;
    }
    public void setTransactions(Set<Transaction> transactions)
    {
        this.transactions = transactions;
    }
}
