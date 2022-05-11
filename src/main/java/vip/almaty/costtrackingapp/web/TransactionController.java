package vip.almaty.costtrackingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import vip.almaty.costtrackingapp.domain.Budget;
import vip.almaty.costtrackingapp.domain.Category;
import vip.almaty.costtrackingapp.domain.Transaction;
import vip.almaty.costtrackingapp.dto.CategoryDto;
import vip.almaty.costtrackingapp.service.BudgetService;
import vip.almaty.costtrackingapp.service.CategoryService;
import vip.almaty.costtrackingapp.service.TransactionService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value= {"/budgets/{budgetId}/groups/{groupId}/categories/{categoryId}/transactions",
        "/budgets/{budgetId}/transactions"})
public class TransactionController
{
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping("")
    public String addTransactionToBudget(@PathVariable Long budgetId,
                                         @PathVariable(required=false) Long groupId,
                                         @PathVariable(required=false) Long categoryId)
    {
        String retUrl = "";
        Transaction tx = new Transaction();
        Budget budget = budgetService.findOne(budgetId);

        tx.setBudget(budget);
        budget.getTransactions().add(tx);

        // old way = new Date();
        // new way = LocalDate.now();  // or LocalDateTime.now();
        tx.setDate(LocalDate.now());

        if (categoryId != null)
        {
            Category category = categoryService.findOne(categoryId);

            tx.setCategory(category);
            category.getTransactions().add(tx);
            retUrl = "/budgets/"+budgetId+"/groups/"+category.getGroup().getId()+"/categories/"+category.getId()+"/transactions";
        } else {
            retUrl = "/budgets/"+budgetId+"/transactions";
        }

        tx = transactionService.save(tx);
        return "redirect:" + retUrl + "/"+tx.getId();
    }

    @GetMapping("{transactionId}")
    public String getTransaction(@PathVariable Long transactionId, ModelMap model)
    {
        Transaction transaction = transactionService.findOne(transactionId);
        model.put("transaction", transaction);
        model.put("budget", transaction.getBudget());
        List<CategoryDto> categories = transaction.getBudget().getGroups()
                .stream()
                .map(group -> group.getCategories())
                .flatMap(Collection::stream)
                .map(category -> new CategoryDto(category.getId().toString(), category.getName()))
                .collect(Collectors.toList());

        model.put("categories", categories);
        return "transaction";
    }

    @PostMapping("{transactionId}")
    public String postTransaction(@ModelAttribute Transaction transaction, @PathVariable Long transactionId)
    {
        transaction = transactionService.save(transaction);
        return "redirect:/budgets/"+transaction.getBudget().getId();
    }

}
