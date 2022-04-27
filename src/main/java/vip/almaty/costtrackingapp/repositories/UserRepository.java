package vip.almaty.costtrackingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.almaty.costtrackingapp.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);



}
