package br.com.matheusbodo.rspt.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.matheusbodo.rspt.entity.enums.Role;

public class SecurityUtil {

	public static String getLoggedUsername() {
		SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }
        
        return authentication.getName();
	}
	
	public static boolean hasRole(Role role) {
        // get security context from thread local
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return false;
        }

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return false;
        }

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (role.name().equals(auth.getAuthority())) {
                return true;
            }
        }

        return false;
    }
}
