package com.codejv.project.api.scheduler.repository;

import com.codejv.project.api.core.model.Schedule;
import com.codejv.project.api.core.repository.ScheduleRepository;
import com.codejv.project.api.scheduler.util.ScheduleCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Schedule Repository Tests")
class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    @DisplayName("save Persist Schedule when successful")
    void save_PersistSchedule_WhenSuccessful() {
        Schedule scheduleToBeSave = ScheduleCreator.createSchedule();
        Schedule scheduleSaved = this.scheduleRepository.save(scheduleToBeSave);

        assertThat(scheduleSaved).isNotNull();
        assertThat(scheduleSaved.getId()).isNotNull();
        assertThat(scheduleSaved.getName()).isEqualTo(scheduleToBeSave.getName());
        assertThat(scheduleSaved.getEmail()).isEqualTo(scheduleToBeSave.getEmail());
        assertThat(scheduleSaved.getTelephoneNumber()).isEqualTo(scheduleToBeSave.getTelephoneNumber());
        assertThat(scheduleSaved.getSchedulerDate()).isEqualTo(scheduleToBeSave.getSchedulerDate());

    }

    @Test
    @DisplayName("save update Schedule when successful")
    void save_UpdateSchedule_WhenSuccessful() {
        Schedule scheduleToBeSave = ScheduleCreator.createSchedule();
        Schedule scheduleSaved = this.scheduleRepository.save(scheduleToBeSave);

        scheduleSaved.setName("Jhon M.");
        scheduleSaved.setEmail("jhon_m@hotmail.com");
        scheduleSaved.setTelephoneNumber(8877998453L);
        scheduleSaved.setSchedulerDate(LocalDate.of(2021, 5, 18));

        Schedule scheduleUpdated = this.scheduleRepository.save(scheduleSaved);

        assertThat(scheduleUpdated).isNotNull();
        assertThat(scheduleUpdated.getId()).isNotNull();
        assertThat(scheduleUpdated.getName()).isEqualTo(scheduleSaved.getName());
        assertThat(scheduleUpdated.getEmail()).isEqualTo(scheduleSaved.getEmail());
        assertThat(scheduleUpdated.getTelephoneNumber()).isEqualTo(scheduleSaved.getTelephoneNumber());
        assertThat(scheduleUpdated.getSchedulerDate()).isEqualTo(scheduleSaved.getSchedulerDate());

    }

    @Test
    @DisplayName("delete update Schedule when successful")
    void delete_RemoveSchedule_WhenSuccessful() {
        Schedule scheduleToBeSave = ScheduleCreator.createSchedule();
        Schedule scheduleSaved = this.scheduleRepository.save(scheduleToBeSave);

        this.scheduleRepository.delete(scheduleSaved);

        Optional<Schedule> scheduleOptional = this.scheduleRepository.findById(scheduleSaved.getId());

        assertThat(scheduleOptional).isEmpty();

    }

    @Test
    @DisplayName("findBySchedulerData return list of Schedule when successful")
    void findBySchedulerData_ReturnListOfSchedule_WhenSuccessful() {
        Schedule scheduleToBeSave = ScheduleCreator.createSchedule();
        Schedule scheduleSaved = this.scheduleRepository.save(scheduleToBeSave);

        List<Schedule> bySchedulerData = this.scheduleRepository.findBySchedulerDate(scheduleSaved.getSchedulerDate());

        assertThat(bySchedulerData)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .contains(scheduleSaved);
        assertThat(bySchedulerData.get(0).getName()).isEqualTo(scheduleSaved.getName());

    }

    @Test
    @DisplayName("findBySchedulerData return empty list when schedule is not found")
    void findBySchedulerData_ReturnEmptyList_WhenScheduleIsNotFound() {
        List<Schedule> bySchedulerData = this.scheduleRepository
                .findBySchedulerDate(LocalDate.of(2021, 5, 18));
        assertThat(bySchedulerData).isEmpty();
    }

    @Test
    @DisplayName("findById returns Schedule by ID when successful")
    void findById_ReturnsSchedule_WhenSuccessful(){
        Schedule scheduleToBeSave = ScheduleCreator.createSchedule();
        Schedule scheduleSaved = this.scheduleRepository.save(scheduleToBeSave);

        Long expectedId = scheduleSaved.getId();
        Optional<Schedule> schedule = scheduleRepository.findById(expectedId);

        assertThat(schedule)
                .isNotNull()
                .isNotEmpty();
        assertThat(schedule.get().getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findById returns empty when Schedule not exist")
    void findById_ReturnEmpty_WhenScheduleDateExist() {
        Optional<Schedule> scheduleOptional = this.scheduleRepository.findById(1L);
        assertThat(scheduleOptional).isEmpty();
    }

    @Test
    @DisplayName("findAll returns an list of Schedule when successful")
    void findAll_ReturnsListOfSchedule_WhenSuccessful(){
        Schedule scheduleToBeSave = ScheduleCreator.createSchedule();
        Schedule scheduleSaved = this.scheduleRepository.save(scheduleToBeSave);

        String expectedName = scheduleSaved.getName();
        Page<Schedule> schedulesPage = scheduleRepository.findAll(PageRequest.of(0, 1));

        assertThat(schedulesPage).isNotNull();
        assertThat(schedulesPage.toList())
                .isNotEmpty()
                .hasSize(1);
        assertThat(schedulesPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findAll return empty list when schedule is not found")
    void findAll_ReturnEmptyList_WhenScheduleIsNotFound() {
        Page<Schedule> schedulesPage = this.scheduleRepository
                .findAll(PageRequest.of(0, 1));
        assertThat(schedulesPage).isEmpty();
    }

}
