package vip.almaty.costtrackingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.almaty.costtrackingapp.domain.Budget;
import vip.almaty.costtrackingapp.domain.User;
import vip.almaty.costtrackingapp.service.BudgetService;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


@Controller
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;


    @RequestMapping(value="", method=RequestMethod.GET)
    public String getBudget(@AuthenticationPrincipal User user, ModelMap model)
    {
        getBudgets(user, model);
        // this should fetch budgets from the database for the logged in user
        return "budgets";
    }

    private void getBudgets(User user, ModelMap model) {
        TreeSet<Budget> budgets = budgetService.getBudgets(user);

        model.put("budgets", budgets);
    }

    @RequestMapping (value = "", method = RequestMethod.POST)
    public @ResponseBody Budget postBudget(@AuthenticationPrincipal User user, ModelMap model){

        Set<User> users = new HashSet<>();
        Set<Budget> budgets = new HashSet<>();
        users.add(user);
        Budget budget = new Budget();
        budgets.add(budget);
        budget.setName("New Budget");
        budget.setUsers(users);

        budgetService.saveBudget(budget);

        getBudgets(user, model);
        return budget;
    }
}