package vip.almaty.costtrackingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import vip.almaty.costtrackingapp.domain.Budget;
import vip.almaty.costtrackingapp.domain.Group;
import vip.almaty.costtrackingapp.domain.User;
import vip.almaty.costtrackingapp.repositories.BudgetRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepo;

    public TreeSet<Budget> getBudgets (@AuthenticationPrincipal User user) {

        Set<User> users = new HashSet<>();
        users.add(user);
        return budgetRepo.findByUsersIn(users);
    }

    public Budget saveBudget (User user, Budget budget) {
        Set<User> users = new HashSet<>();
        Set<Budget> budgets = new HashSet<>();
        users.add(user);
        long count = getBudgetCount(users);
        budgets.add(budget);
        budget.setName("New Budget # " + ++count);
        budget.setUsers(users);

        Group group = new Group();

        group.setBudget(budget);
        group.setName("Savings");

        budget.getGroups().add(group);

        user.setBudgets(budgets);
        return budgetRepo.save(budget);
    }

    private long getBudgetCount(Set<User> users) {
        return budgetRepo.countByUsersIn(users);
    }

    public Budget findOne(Long budgetId) {

        Optional<Budget> optionalBudget = budgetRepo.findById(budgetId);

        Budget budget = optionalBudget.get();
        return budget;
    }
}
