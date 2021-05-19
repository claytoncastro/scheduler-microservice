package com.codejv.project.api.scheduler.endpoints.service.impl;

import com.codejv.project.api.core.model.Schedule;
import com.codejv.project.api.core.repository.ScheduleRepository;
import com.codejv.project.api.scheduler.endpoints.requests.dto.SchedulePostRequestBody;
import com.codejv.project.api.scheduler.exceptions.ResourceAlreadyExistException;
import com.codejv.project.api.scheduler.exceptions.ResourceNotFoundException;
import com.codejv.project.api.scheduler.util.ScheduleCreator;
import com.codejv.project.api.scheduler.util.SchedulePostRequestBodyCreator;
import com.codejv.project.api.scheduler.util.SchedulePutRequestBodyCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
@DisplayName("Test Schedule Service")
class ScheduleServiceImplTest {

    @InjectMocks
    private ScheduleServiceImpl scheduleService;
    @Mock
    private ScheduleRepository scheduleRepositoryMok;

    @BeforeEach
    void setUp() {
        BDDMockito.when(scheduleRepositoryMok.save(ArgumentMatchers.any(Schedule.class)))
                .thenReturn(ScheduleCreator.createValidSchedule());
        PageImpl<Schedule> schedulesPage = new PageImpl<>(Collections.singletonList(ScheduleCreator.createSchedule()));
        BDDMockito.when(scheduleRepositoryMok.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(schedulesPage);
        BDDMockito.when(scheduleRepositoryMok.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ScheduleCreator.createValidSchedule()));
        BDDMockito.doNothing().when(scheduleRepositoryMok).delete(ArgumentMatchers.any(Schedule.class));
    }

    @Test
    @DisplayName("save returns Schedule when successful")
    void save_ReturnsSchedule_WhenSuccessful(){
        SchedulePostRequestBody scheduleToBeSaved = SchedulePostRequestBodyCreator.createSchedulePostRequestBody();
        Schedule scheduleSaved = scheduleService.save(scheduleToBeSaved);

        assertThat(scheduleSaved)
                .isNotNull()
                .isEqualTo(ScheduleCreator.createValidSchedule());
        assertThat(scheduleSaved.getName()).isEqualTo(scheduleToBeSaved.getName());
        assertThat(scheduleSaved.getEmail()).isEqualTo(scheduleToBeSaved.getEmail());
        assertThat(scheduleSaved.getSchedulerDate()).isEqualTo(scheduleToBeSaved.getSchedulerData());
        assertThat(scheduleSaved.getTelephoneNumber()).isEqualTo(scheduleToBeSaved.getTelephoneNumber());

    }

    @Test
    @DisplayName("save throw ResourceAlreadyExistException when scheduleDate already exist")
    void save_ThrowResourceAlreadyExistException_WhenScheduleDateExist() {
        BDDMockito.when(scheduleRepositoryMok.findBySchedulerData(ArgumentMatchers.any(LocalDate.class)))
                .thenReturn(Collections.singletonList(ScheduleCreator.createValidSchedule()));

        assertThatExceptionOfType(ResourceAlreadyExistException.class)
                .isThrownBy(() -> this.scheduleService.save(SchedulePostRequestBodyCreator.createSchedulePostRequestBody()));
    }

    @Test
    @DisplayName("update returns Schedule when successful")
    void update_ReturnsSchedule_WhenSuccessful(){
        assertThatCode(() -> scheduleService.update(SchedulePutRequestBodyCreator.createSchedulePutRequestBody()))
                .doesNotThrowAnyException();

        Schedule responseEntity = scheduleService.update(SchedulePutRequestBodyCreator.createSchedulePutRequestBody());
        assertThat(responseEntity)
                .isNotNull()
                .isEqualTo(ScheduleCreator.createValidSchedule());
    }

    @Test
    @DisplayName("findAll returns an list of Schedule when successful")
    void findAll_ReturnsListOfSchedule_WhenSuccessful(){
        String expectedName = ScheduleCreator.createSchedule().getName();
        Page<Schedule> schedulesPage = scheduleService.findAll(PageRequest.of(1, 1));

        assertThat(schedulesPage).isNotNull();
        assertThat(schedulesPage.toList())
                .isNotEmpty()
                .hasSize(1);
        assertThat(schedulesPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns Schedule by ID when successful")
    void findById_ReturnsSchedule_WhenSuccessful(){
        Long expectedId = ScheduleCreator.createValidSchedule().getId();
        Schedule schedule = scheduleService.findByIdOrThrowBadRequestException(1L);

        assertThat(schedule).isNotNull();
        assertThat(schedule.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findById throw ResourceNotFoundException when Schedule not exist")
    void findById_ThrowResourceNotFoundException_WhenScheduleDateExist() {
        BDDMockito.when(scheduleRepositoryMok.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> this.scheduleService.findByIdOrThrowBadRequestException(1L));
    }

    @Test
    @DisplayName("delete removes Schedule when successful")
    void delete_RemovesSchedule_WhenSuccessful(){

        assertThatCode(() ->scheduleService.delete(1L)).doesNotThrowAnyException();

    }

}