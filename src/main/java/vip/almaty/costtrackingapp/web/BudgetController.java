package vip.almaty.costtrackingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
    public String getBudgets(@AuthenticationPrincipal User user, ModelMap model)
    {
        populateUserBudgetsOnModel(user, model);
        // this should fetch budgets from the database for the logged in user
        return "budgets";
    }

    @GetMapping("{budgetId}")
    public String getBudget (@PathVariable Long budgetId, ModelMap model){

        Budget budget = budgetService.findOne(budgetId);
        model.put("budget", budget);
        return "budget";
    }

    private void populateUserBudgetsOnModel(User user, ModelMap model) {
        TreeSet<Budget> budgets = budgetService.getBudgets(user);

        model.put("budgets", budgets);
    }

    @RequestMapping (value = "", method = RequestMethod.POST)
    public @ResponseBody Budget postBudget(@AuthenticationPrincipal User user, ModelMap model){

        Budget budget = new Budget();

        budgetService.saveBudget(user, budget);

        populateUserBudgetsOnModel(user, model);
        return budget;
    }
}
