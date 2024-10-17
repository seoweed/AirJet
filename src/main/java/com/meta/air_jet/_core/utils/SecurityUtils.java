package com.meta.air_jet._core.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String getCurrentUserId() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name.equals("anonymousUser")) {
            throw new SecurityException("Anonymous User");
        }
        return name;
    }
}
