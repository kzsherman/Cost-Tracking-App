package vip.almaty.costtrackingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import vip.almaty.costtrackingapp.domain.Category;
import vip.almaty.costtrackingapp.domain.Group;
import vip.almaty.costtrackingapp.service.CategoryService;
import vip.almaty.costtrackingapp.service.GroupService;


@Controller
@RequestMapping("/budgets/{budgetId}/groups/{groupId}/categories")
public class CategoryController
{
    @Autowired
    private GroupService groupService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public String postCategory(@PathVariable Long groupId)
    {
        Category category = new Category();

        Group group = groupService.findOne(groupId);

        category.setGroup(group);
        group.getCategories().add(category);
        category.setName("Test Category");

        category = categoryService.saveCategory(category);

        return "redirect:/budgets/"+group.getBudget().getId()+"/groups/"+group.getId()+"/categories/"+category.getId();
    }

    @GetMapping("{categoryId}")
    public String getCategory(@PathVariable Long categoryId, ModelMap model)
    {
        Category category = categoryService.findOne(categoryId);

        model.put("category", category);
        model.put("group", category.getGroup());

        return "category";
    }

    @PostMapping("{categoryId}")
    public String saveCategory (@ModelAttribute Category category, @PathVariable Long categoryId)
    {
        Category categoryFromDB = categoryService.findOne(categoryId);

        categoryFromDB.setName(category.getName());
        categoryFromDB.setBudget(category.getBudget());

        categoryFromDB = categoryService.saveCategory(categoryFromDB);

        Long budgetId = categoryFromDB.getGroup().getBudget().getId();

        return "redirect:/budgets/"+budgetId;
    }
}
