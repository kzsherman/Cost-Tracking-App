package vip.almaty.costtrackingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.almaty.costtrackingapp.domain.Budget;
import vip.almaty.costtrackingapp.domain.Group;
import vip.almaty.costtrackingapp.repositories.GroupRepository;
import vip.almaty.costtrackingapp.service.BudgetService;

import java.util.Optional;

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

    public Group save(Group group)
    {
        return groupRepo.save(group);
    }

    public Group findOne(Long groupId)
    {
      Optional<Group> groupOptional = groupRepo.findById(groupId);

      Group group = groupOptional.get();

      return group;

    }
}
