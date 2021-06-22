package com.mortgert.services;

import com.mortgert.data.models.Privilege;
import com.mortgert.data.models.Role;
import com.mortgert.data.models.User;
import com.mortgert.data.repos.PrivilegeRepository;
import com.mortgert.data.repos.RoleRepository;
import com.mortgert.data.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true,
                    getAuthorities(Collections.singletonList(
                            roleRepository.findByName("ROLE_USER"))));
        }
        user.setRoles(roleRepository.findByUsers_Username(user.getUsername()));
        for(Role role: user.getRoles()){
            role.setPrivileges(privilegeRepository.findAllByRoles(role));
        }
        Collection<Role> roleList = user.getRoles();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), user.isEnabled(),
                                                        true,true,true, getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles){
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles){
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();

        for(Role role:roles){
            collection.addAll(role.getPrivileges());
        }
        for(Privilege item: collection){
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }


}
