package vip.almaty.costtrackingapp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import vip.almaty.costtrackingapp.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>
{

}
