package vip.almaty.costtrackingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.almaty.costtrackingapp.domain.Budget;
import vip.almaty.costtrackingapp.domain.User;

import java.util.Set;
import java.util.TreeSet;

public interface BudgetRepository extends JpaRepository <Budget, Long> {

    TreeSet<Budget> findByUsersIn (Set<User> users);

}
