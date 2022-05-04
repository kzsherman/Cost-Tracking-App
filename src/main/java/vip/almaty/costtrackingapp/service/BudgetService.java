package vip.almaty.costtrackingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import vip.almaty.costtrackingapp.domain.Budget;
import vip.almaty.costtrackingapp.domain.User;
import vip.almaty.costtrackingapp.repositories.BudgetRepository;

import java.util.HashSet;
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

    public Budget saveBudget (Budget budget) {
        return budgetRepo.save(budget);
    }
}
