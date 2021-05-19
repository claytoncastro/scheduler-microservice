package com.codejv.project.api.scheduler.util;

import com.codejv.project.api.core.model.ApplicationUser;

public class ApplicationUserCreator {

    public static ApplicationUser createApplicationUser() {
        return ApplicationUser.builder()
                .name("Jhon Mayer")
                .authorities("ROLE_ADMIN,ROLE_USER")
                .username("jhon")
                .password("test")
                .build();
    }

}
