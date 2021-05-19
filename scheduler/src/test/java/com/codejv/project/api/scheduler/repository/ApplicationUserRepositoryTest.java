package com.codejv.project.api.scheduler.repository;

import com.codejv.project.api.core.model.ApplicationUser;
import com.codejv.project.api.core.repository.ApplicationUserRepository;
import com.codejv.project.api.scheduler.util.ApplicationUserCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("ApplicationUser Repository Tests")
class ApplicationUserRepositoryTest {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Test
    @DisplayName("findBySchedulerData return list of ApplicationUser when successful")
    void findBySchedulerData_ReturnListOfSchedule_WhenSuccessful() {
        ApplicationUser applicationUserToBeSave = ApplicationUserCreator.createApplicationUser();
        ApplicationUser applicationUserSaved = this.applicationUserRepository.save(applicationUserToBeSave);

        ApplicationUser applicationUser = this.applicationUserRepository
                .findByUsername(applicationUserSaved.getUsername());

        assertThat(applicationUser)
                .isNotNull();
        assertThat(applicationUser.getName()).isEqualTo(applicationUserSaved.getName());
        assertThat(applicationUser.getUsername()).isEqualTo(applicationUserSaved.getUsername());
        assertThat(applicationUser.getAuthorities()).isEqualTo(applicationUserSaved.getAuthorities());
        assertThat(applicationUser.getPassword()).isEqualTo(applicationUserSaved.getPassword());

    }

    @Test
    @DisplayName("findBySchedulerData return null when ApplicationUser is not found")
    void findBySchedulerData_ReturnEmptyList_WhenScheduleIsNotFound() {
        ApplicationUser applicationUser = this.applicationUserRepository
                .findByUsername("test");
        assertThat(applicationUser).isNull();
    }

}
