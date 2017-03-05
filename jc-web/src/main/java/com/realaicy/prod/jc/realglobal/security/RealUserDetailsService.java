package com.realaicy.prod.jc.realglobal.security;

import com.realaicy.prod.jc.modules.system.model.Role;
import com.realaicy.prod.jc.modules.system.model.UserSec;
import com.realaicy.prod.jc.modules.system.service.UserSecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by realaicy on 16/7/14.
 * XXX
 */
@Service("R2")
public class RealUserDetailsService implements UserDetailsService {

    //@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
    private final UserSecService userSecService;

    @Autowired
    public RealUserDetailsService(UserSecService userSecService) {
        this.userSecService = userSecService;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserSec userSec;
        Collection<GrantedAuthority> grantedAuthorities;

        HashSet<String> realAuthorities = new HashSet<>();

        try {
            userSec = userSecService.findByUsername(userName);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user select fail");
        }
        if (userSec == null) {
            throw new UsernameNotFoundException("no user found");
        } else {
            try {

                if (userSec.getRoles() == null || userSec.getRoles().size() < 1) {
                    grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("");
                } else {
                    StringBuilder commaBuilder = new StringBuilder();
                    for (Role role : userSec.getRoles()) {
                        commaBuilder.append(role.getName()).append(",");
                        if (role.getRealauthorities() != null && !role.getRealauthorities().equals("")) {
                            Collections.addAll(realAuthorities, role.getRealauthorities().split(","));
                        }

                    }
                    String authorities = commaBuilder.substring(0, commaBuilder.length() - 1);
                    grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                }

                return new RealUserDetails(userSec.getId(), userSec.getUsername(), userSec.getPassword(),
                        userSec.getNickname(), userSec.isEnabled(), userSec.isAccountNonExpired(),
                        userSec.isCredentialsNonExpired(), userSec.isAccountNonLocked(),
                        grantedAuthorities, realAuthorities);
            } catch (Exception e) {
                e.printStackTrace();
                throw new UsernameNotFoundException("user role select fail");
            }
        }
    }
}
