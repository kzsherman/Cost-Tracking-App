package vip.almaty.costtrackingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.almaty.costtrackingapp.domain.Category;
import vip.almaty.costtrackingapp.domain.Group;
import vip.almaty.costtrackingapp.repositories.CategoryRepository;

import java.util.Optional;


@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepo;

    public Category saveCategory (Category category)
    {
        return categoryRepo.save(category);
    }

    public Category findOne(Long categoryId) {

        Optional<Category> categoryOptional = categoryRepo.findById(categoryId);

        Category category = categoryOptional.get();

        return category;
    }
}
