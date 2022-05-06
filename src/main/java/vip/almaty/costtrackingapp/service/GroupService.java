package vip.almaty.costtrackingapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.almaty.costtrackingapp.domain.Budget;
import vip.almaty.costtrackingapp.domain.Group;
import vip.almaty.costtrackingapp.repositories.GroupRepository;
import vip.almaty.costtrackingapp.service.BudgetService;

@Service
public class GroupService
{
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private GroupRepository groupRepo;

    public Group createNewGroup(Long budgetId)
    {
        Group group = new Group();

        Budget budget = budgetService.findOne(budgetId);
        group.setBudget(budget);
        budget.getGroups().add(group);

        return save(group);
    }

    private Group save(Group group)
    {
        return groupRepo.save(group);
    }

    public Group findOne(Long groupId)
    {
        return groupRepo.findOne(groupId);
    }
}
