package vip.almaty.costtrackingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.almaty.costtrackingapp.domain.Authority;
import vip.almaty.costtrackingapp.domain.User;
import vip.almaty.costtrackingapp.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;



    public User saveUser(User user){

        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        authority.setUser(user);

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);

        user.setAuthorities(authorities);
        user = userRepo.save(user);

       return user;
    }

}
