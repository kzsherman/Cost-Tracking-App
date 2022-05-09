package vip.almaty.costtrackingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import vip.almaty.costtrackingapp.domain.Budget;
import vip.almaty.costtrackingapp.domain.User;
import vip.almaty.costtrackingapp.service.BudgetService;

import java.util.*;


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

        Calendar cal = Calendar.getInstance();

        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0,0,0);
        cal.set(Calendar.MILLISECOND, 0);

        Date firstOfMonth = cal.getTime();

        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, 0);

        Date lastOfMonth = cal.getTime();

        budget.setStartDate(firstOfMonth);
        budget.setEndDate(lastOfMonth);
        budget = budgetService.saveBudget(user, budget);

        budgetService.saveBudget(user, budget);

        populateUserBudgetsOnModel(user, model);
        return budget;
    }
}
