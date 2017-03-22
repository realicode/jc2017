package com.realaicy.prod.jc.realglobal.security;

import com.realaicy.prod.jc.modules.system.model.Role;
import com.realaicy.prod.jc.modules.system.model.User;
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
//    @Cacheable(key = "#userName", cacheNames = "User")
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        String regEx = "[`~!@#$%^&*()+=|{}':;,\\[\\].<>/?！￥…（）—【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(userName);
        userName = m.replaceAll("").trim();
        User user;
        Collection<GrantedAuthority> grantedAuthorities;

        HashSet<String> realAuthorities = new HashSet<>();

        try {
            user = userService.findByUsername(userName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("user select fail");
        }
        if (user == null) {
            throw new UsernameNotFoundException("no user found");
        } else {
            try {

                if (user.getRoles() == null || user.getRoles().size() < 1) {
                    grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("");
                } else {
                    StringBuilder commaBuilder = new StringBuilder();
                    for (Role role : user.getRoles()) {
                        commaBuilder.append(role.getName()).append(",");
                        if (role.getRealauthorities() != null && !role.getRealauthorities().equals("")) {
                            Collections.addAll(realAuthorities, role.getRealauthorities().split(","));
                        }

                    }
                    String authorities = commaBuilder.substring(0, commaBuilder.length() - 1);
                    grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                }

                return new RealUserDetails(user.getId(), user.getUsername(), user.getPassword(),
                        "fake", user.isEnabled(), user.isAccountNonExpired(),
                        user.isCredentialsNonExpired(), user.isAccountNonLocked(),
                        grantedAuthorities, realAuthorities);
            } catch (Exception e) {
                e.printStackTrace();
                throw new UsernameNotFoundException("user role select fail");
            }
        }
    }
}
