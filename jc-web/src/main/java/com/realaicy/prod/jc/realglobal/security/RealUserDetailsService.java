package com.realaicy.prod.jc.realglobal.security;

import com.realaicy.prod.jc.modules.system.model.Role;
import com.realaicy.prod.jc.modules.system.model.UserSec;
import com.realaicy.prod.jc.modules.system.service.UserService;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by realaicy on 16/7/14.
 * XXX
 */
@Service("R2")
public class RealUserDetailsService implements UserDetailsService {

    //@SuppressWarnings("SpringAutowiredFieldsWarningInspection")
    private final UserService userService;

    @Autowired
    public RealUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        String regEx = "[`~!@#$%^&*()+=|{}':;,\\[\\].<>/?！￥…（）—【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(userName);
        userName = m.replaceAll("").trim();
        UserSec usersec;
        Collection<GrantedAuthority> grantedAuthorities;

        HashSet<String> realAuthorities = new HashSet<>();

        try {
            usersec = userService.getUserSecFromUsername(userName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("user select fail");
        }
        if (usersec == null) {
            throw new UsernameNotFoundException("no user found");
        } else {
            try {

                if (usersec.getRoles() == null || usersec.getRoles().size() < 1) {
                    grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("");
                } else {
                    StringBuilder commaBuilder = new StringBuilder();
                    for (Role role : usersec.getRoles()) {
                        commaBuilder.append(role.getName()).append(",");
                        if (role.getRealauthorities() != null && !role.getRealauthorities().equals("")) {
                            Collections.addAll(realAuthorities, role.getRealauthorities().split(","));
                        }

                    }
                    String authorities = commaBuilder.substring(0, commaBuilder.length() - 1);
                    grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                }

                return new RealUserDetails(usersec.getId(), usersec.getUsername(), usersec.getPassword(),
                        "fake", usersec.isEnabled(), usersec.isAccountNonExpired(),
                        usersec.isCredentialsNonExpired(), usersec.isAccountNonLocked(),
                        grantedAuthorities, realAuthorities);
            } catch (Exception e) {
                e.printStackTrace();
                throw new UsernameNotFoundException("user role select fail");
            }
        }
    }
}
