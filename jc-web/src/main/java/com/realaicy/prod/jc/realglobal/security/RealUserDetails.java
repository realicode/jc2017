package com.realaicy.prod.jc.realglobal.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by realaicy on 16/7/14.
 * xxx
 */

public class RealUserDetails extends User {

    private final BigInteger id;
    private final HashSet<String> realAuthorities;

    RealUserDetails(final BigInteger id, final String username, final String password,
                    final String nickName, boolean enabled,
                    boolean accountNonExpired, boolean credentialsNonExpired,
                    boolean accountNonLocked,
                    final Collection<? extends GrantedAuthority> authorities, HashSet<String> realAuthorities) {
        super(username, password, enabled, true, true, true, authorities);
        this.realAuthorities = realAuthorities;
        this.id = id;
    }


    public HashSet<String> getRealAuthorities() {
        return realAuthorities;
    }

    public BigInteger getId() {
        return id;
    }


}
