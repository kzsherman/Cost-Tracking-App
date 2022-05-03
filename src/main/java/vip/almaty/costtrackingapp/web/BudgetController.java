package vip.almaty.costtrackingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vip.almaty.costtrackingapp.domain.Budget;
import vip.almaty.costtrackingapp.domain.User;
import vip.almaty.costtrackingapp.service.BudgetService;

import java.util.TreeSet;


@Controller
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;


    @RequestMapping(value="", method=RequestMethod.GET)
    public String getBudget(@AuthenticationPrincipal User user, ModelMap model)
    {
        TreeSet<Budget> budgets = budgetService.getBudgets(user);

        model.put("budgets", budgets);
        // this should fetch budgets from the database for the logged in user
        return "budgets";
    }
}
