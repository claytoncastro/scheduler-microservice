package com.codejv.project.api.scheduler.endpoints.controller;

import com.codejv.project.api.core.model.Schedule;
import com.codejv.project.api.scheduler.endpoints.requests.dto.SchedulePostRequestBody;
import com.codejv.project.api.scheduler.endpoints.requests.dto.SchedulePutRequestBody;
import com.codejv.project.api.scheduler.endpoints.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping(path = "/admin")
    @Transactional(rollbackFor = Exception.class)
    @Operation(summary = "Save an Schedule",
            description = "This method saves an Schedule. This application does not allow you to save Schedule with " +
                    "the same scheduleDate, you must change the scheduleDate before save")
    public ResponseEntity<Schedule> save(@Valid @RequestBody SchedulePostRequestBody schedulePostRequestBody) {
        return new ResponseEntity<>(scheduleService.save(schedulePostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping(path = "/admin")
    @Transactional(rollbackFor = Exception.class)
    @Operation(summary = "Update an Schedule", description = "This method updates an Schedule")
    public ResponseEntity<Schedule> update(@Valid @RequestBody SchedulePutRequestBody schedulePutRequestBody) {
        return new ResponseEntity<>(scheduleService.update(schedulePutRequestBody), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "List all Schedule", description = "This method list all Schedules")
    public ResponseEntity<Page<Schedule>> findAll(@Parameter(hidden = true) Pageable pageable) {
        return new ResponseEntity<>(scheduleService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Find an Schedule by id", description = "This method find an Schedule by id")
    public ResponseEntity<Schedule> findById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findByIdOrThrowBadRequestException(id), HttpStatus.FOUND);
    }

    @DeleteMapping(path = "/admin/{id}")
    @Transactional(rollbackFor = Exception.class)
    @Operation(summary = "Delete an Schedule", description = "This method delete an Schedule")
    public ResponseEntity<Schedule> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
