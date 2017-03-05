package com.realaicy.prod.jc.uitl;

import com.google.common.collect.ImmutableSet;
import com.realaicy.prod.jc.realglobal.security.RealUserDetails;
import com.realaicy.prod.jc.realglobal.security.SpringSecurityPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public final class SpringSecurityUtil {

    public static final String ANONYMOUS_USER = "anonymousUser";
    public static final String SEC_CLIENT = "sec_client";

    private SpringSecurityUtil() {
        throw new AssertionError();
    }

    // API

    /*public static User authenticate(final String key, final String uuid) {
        final SpringSecurityPrincipal principal = new SpringSecurityPrincipal(randomAlphabetic(6),
        randomAlphabetic(6), true, Lists.<GrantedAuthority>newArrayList(), uuid);
        SecurityContextHolder.getContext().setAuthentication(new RunAsUserToken(key,
        principal, null, Lists.<GrantedAuthority>newArrayList(), null));

        return principal;
    }*/

    //

    /**
     * Gets the current user details.
     *
     * @return the current user details or null if can't be retrieved.
     */
    public static UserDetails getCurrentUserDetails() {
        final Authentication authentication = getCurrentAuthentication();
        if (authentication == null) {
            return null;
        }

        final Object principal = authentication.getPrincipal();
        if (principal == null) {
            return null;
        }
        if (principal instanceof UserDetails) {
            return (UserDetails) principal;
        }

        return null;
    }

    public static RealUserDetails getCurrentRealUserDetails() {

        final Authentication authentication = getCurrentAuthentication();
        if (authentication == null) {
            return null;
        }

        final Object principal = authentication.getPrincipal();
        if (principal == null) {
            return null;
        }
        if (principal instanceof RealUserDetails) {
            return (RealUserDetails) principal;
        }

        return null;
    }


    public static Authentication getCurrentAuthentication() {
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            return null;
        }
        return securityContext.getAuthentication();
    }

    public static RealUserDetails getCurrentPrincipal() {
        final Authentication currentAuthentication = getCurrentAuthentication();
        if (currentAuthentication == null) {
            return null;
        }
        final Object principal = currentAuthentication.getPrincipal();
        if (principal == null) {
            return null;
        }

        return (RealUserDetails) principal;
    }

    public static SpringSecurityPrincipal getCurrentPrincipalV2() {
        final Authentication currentAuthentication = getCurrentAuthentication();
        if (currentAuthentication == null) {
            return null;
        }
        final Object principal = currentAuthentication.getPrincipal();
        if (principal == null) {
            return null;
        }

        return (SpringSecurityPrincipal) principal;
    }

    public static String getNameOfCurrentPrincipal() {
        final Authentication authentication = getCurrentAuthentication();
        if (authentication == null) {
            return null;
        }

        return authentication.getName();
    }

    public static String getUuidOfCurrentPrincipal() {
        final SpringSecurityPrincipal currentPrincipal = getCurrentPrincipalV2();
        if (currentPrincipal == null) {
            return null;
        }

        return currentPrincipal.getUuid();
    }

    // is?

    /**
     * Check if current user is authenticated.
     *
     * @return true if user is authenticated.
     */
    public static boolean isAuthenticated() {
        return SpringSecurityUtil.getCurrentUserDetails() != null;
    }

    /**
     * Check if current user is anonymous guest.
     *
     * @return true if user is anonymous guest.
     */
    public static boolean isAnonymous() {
        return SpringSecurityUtil.getCurrentUserDetails() == null;
    }

    // has?

    /**
     * Check if current user has specified role.
     *
     * @param privilege the role to check if user has.
     * @return true if user has specified role, otherwise false.
     */
    public static boolean hasPrivilege(final String privilege) {

        final RealUserDetails realUserDetails = SpringSecurityUtil.getCurrentRealUserDetails();
        return realUserDetails != null
                && (realUserDetails.getRealAuthorities().contains("superadmin")
                || realUserDetails.getRealAuthorities().contains(privilege));
    }

    /**
     * Check if current user has specified role.
     *
     * @param privilege the role to check if user has.
     * @return true if user has specified role, otherwise false.
     */
    public static boolean hasRole(final String privilege) {
        final UserDetails userDetails = SpringSecurityUtil.getCurrentUserDetails();
        if (userDetails != null) {
            for (final GrantedAuthority each : userDetails.getAuthorities()) {
                if (each.getAuthority().equals(privilege)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Check if current user has any role of specified.
     *
     * @param privileges the array of roles.
     * @return true if has any role, otherwise false.
     */
    public static boolean hasAnyPrivilege(final String... privileges) {
        final UserDetails userDetails = SpringSecurityUtil.getCurrentUserDetails();
        if (userDetails != null) {
            final Set<String> rolesSet = ImmutableSet.copyOf(privileges);
            for (final GrantedAuthority each : userDetails.getAuthorities()) {
                if (rolesSet.contains(each.getAuthority())) {
                    return true;
                }
            }
        }

        return false;
    }

    // auth


}
