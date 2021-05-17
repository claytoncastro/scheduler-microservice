package com.codejv.project.api.scheduler.endpoints.controller;

import com.codejv.project.api.core.model.Schedule;
import com.codejv.project.api.scheduler.endpoints.requests.dto.SchedulePostRequestBody;
import com.codejv.project.api.scheduler.endpoints.requests.dto.SchedulePutRequestBody;
import com.codejv.project.api.scheduler.endpoints.service.ScheduleService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(SpringExtension.class)
@DisplayName("Test Schedule Controller")
class ScheduleControllerTest {

    @InjectMocks
    private ScheduleController scheduleController;
    @Mock
    private ScheduleService scheduleServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(scheduleServiceMock.save(ArgumentMatchers.any(SchedulePostRequestBody.class)))
                .thenReturn(ScheduleCreator.createValidSchedule());
        BDDMockito.when(scheduleServiceMock.update(ArgumentMatchers.any(SchedulePutRequestBody.class)))
                .thenReturn(ScheduleCreator.createValidSchedule());
        PageImpl<Schedule> schedulesPage = new PageImpl<>(Collections.singletonList(ScheduleCreator.createSchedule()));
        BDDMockito.when(scheduleServiceMock.findAll(ArgumentMatchers.any()))
                .thenReturn(schedulesPage);
        BDDMockito.when(scheduleServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(ScheduleCreator.createValidSchedule());
        BDDMockito.doNothing().when(scheduleServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("save returns Schedule when successful")
    void save_ReturnsSchedule_WhenSuccessful(){
        Schedule scheduleSaved = scheduleController.save(SchedulePostRequestBodyCreator.createSchedulePostRequestBody()).getBody();

        assertThat(scheduleSaved)
                .isNotNull()
                .isEqualTo(ScheduleCreator.createValidSchedule());
    }

    @Test
    @DisplayName("update returns Schedule when successful")
    void update_ReturnsSchedule_WhenSuccessful(){
        ResponseEntity<Schedule> responseEntity = scheduleController
                .update(SchedulePutRequestBodyCreator.createSchedulePutRequestBody());

        assertThat(responseEntity.getBody())
                .isNotNull()
                .isEqualTo(ScheduleCreator.createValidSchedule());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("findAll returns an list of Schedule when successful")
    void findAll_ReturnsListOfSchedule_WhenSuccessful(){
        String expectedName = ScheduleCreator.createSchedule().getName();
        Page<Schedule> schedulesPage = scheduleController.findAll(null).getBody();

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
        Schedule schedule = scheduleController.findById(1L).getBody();

        assertThat(schedule).isNotNull();
        assertThat(schedule.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("delete removes Schedule when successful")
    void delete_RemovesSchedule_WhenSuccessful(){

        assertThatCode(() ->scheduleController.delete(1L)).doesNotThrowAnyException();

        ResponseEntity<Schedule> entity = scheduleController.delete(1L);

        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

}