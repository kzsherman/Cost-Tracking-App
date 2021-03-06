package vip.almaty.costtrackingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import vip.almaty.costtrackingapp.domain.Budget;
import vip.almaty.costtrackingapp.domain.User;
import vip.almaty.costtrackingapp.service.BudgetService;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;


@Controller
@RequestMapping("/budgets")
public class BudgetController
{
    @Autowired
    private BudgetService budgetService;

    @RequestMapping(value="", method=RequestMethod.GET)
    public String getBudgets(@AuthenticationPrincipal User user, ModelMap model)
    {
        // this should fetch budgets from the database for the logged in user
        populateUserBudgetsOnModel(user, model);

        return "budgets";
    }

    @GetMapping("{budgetId}")
    public String getBudget(@PathVariable Long budgetId, ModelMap model)
    {
        Budget budget = budgetService.findOne(budgetId);

        boolean hasCategories = budget.getGroups().stream()
                .filter(group -> group.getCategories().size() > 0)
                .count() > 0;
        model.put("budget", budget);
        model.put("hasCategories", hasCategories);

        return "budget";
    }

    private void populateUserBudgetsOnModel(User user, ModelMap model)
    {
        TreeSet<Budget> budgets = budgetService.getBudgets(user);

        model.put("budgets", budgets);
    }

    @PutMapping("{budgetId}")
    public @ResponseBody void putBudget(@AuthenticationPrincipal User user, @RequestParam String startDate,
                                        @RequestParam String endDate, @PathVariable Long budgetId) throws ParseException
    {
        Budget budget = budgetService.findOne(budgetId);

        budget.setStartDate(budgetService.convertStringToDate(startDate));
        budget.setEndDate(budgetService.convertStringToDate(endDate));

        budgetService.saveBudget(user, budget);
    }


    @PostMapping("")
    public @ResponseBody Budget postBudget(@AuthenticationPrincipal User user, ModelMap model)
    {
        Budget budget = new Budget();

        LocalDate firstOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate lastOfMonth = LocalDate.now().withDayOfMonth(firstOfMonth.lengthOfMonth());

        budget.setStartDate(firstOfMonth);
        budget.setEndDate(lastOfMonth);
        budget = budgetService.saveBudget(user, budget);

        populateUserBudgetsOnModel(user, model);

        return budget;
    }
}
