package com.codejv.project.api.scheduler.endpoints.service.impl;

import com.codejv.project.api.core.model.ApplicationUser;
import com.codejv.project.api.core.repository.ApplicationUserRepository;
import com.codejv.project.api.scheduler.util.ApplicationUserCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
@DisplayName("Test ApplicationUserDetails Service")
class ApplicationUserDetailsServiceImplTest {

    @InjectMocks
    private ApplicationUserDetailsServiceImpl userDetailsService;
    @Mock
    private ApplicationUserRepository applicationUserRepositoryMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(applicationUserRepositoryMock.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(ApplicationUserCreator.createApplicationUser());
    }

    @Test
    @DisplayName("loadUserByUsername returns ApplicationUser when successful")
    void loadUserByUsername_ReturnApplicationUser_WhenSuccessful() {
        ApplicationUser applicationUserExpected = ApplicationUserCreator.createApplicationUser();
        UserDetails userDetails = userDetailsService.loadUserByUsername("Test");

        assertThat(userDetails)
                .isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(applicationUserExpected.getUsername());
        assertThat(userDetails.getPassword()).isEqualTo(applicationUserExpected.getPassword());
        assertThat(userDetails.getAuthorities()).isEqualTo(applicationUserExpected.getAuthorities());
    }

    @Test
    @DisplayName("loadUserByUsername throw UsernameNotFoundException when ApplicationUserIsNotFound")
    void loadUserByUsername_ThrowUsernameNotFoundException_WhenApplicationUserIsNotFound() {
        BDDMockito.when(applicationUserRepositoryMock.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(null);

        assertThatExceptionOfType(UsernameNotFoundException.class)
                .isThrownBy(() -> this.userDetailsService.loadUserByUsername("Test"));
    }



}