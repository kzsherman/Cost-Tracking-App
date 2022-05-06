package vip.almaty.costtrackingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.almaty.costtrackingapp.domain.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
