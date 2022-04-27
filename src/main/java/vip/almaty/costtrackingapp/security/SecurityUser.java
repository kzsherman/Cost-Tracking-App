package vip.almaty.costtrackingapp.security;

import org.springframework.security.core.userdetails.UserDetails;
import vip.almaty.costtrackingapp.domain.Authority;
import vip.almaty.costtrackingapp.domain.User;

import java.util.Set;

public class SecurityUser extends User implements UserDetails {

    public SecurityUser() {
    }

    public SecurityUser(User user) {
        this.setAuthorities(user.getAuthorities());
        this.setBudgets(user.getBudgets());
        this.setId(user.getId());
        this.setPassword(user.getPassword());
        this.setUsername(user.getUsername());
    }

    @Override
    public Set<Authority> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
