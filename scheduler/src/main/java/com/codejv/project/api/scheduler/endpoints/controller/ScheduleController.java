package com.codejv.project.api.scheduler.endpoints.controller;

import com.codejv.project.api.core.model.Schedule;
import com.codejv.project.api.scheduler.endpoints.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "v1/admin/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Schedule> save(@Valid @RequestBody Schedule schedule) {
        return new ResponseEntity<>(scheduleService.save(schedule), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Schedule> update(@Valid @RequestBody Schedule schedule) {
        return new ResponseEntity<>(scheduleService.update(schedule), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Schedule>> findAll(Pageable pageable) {
        return new ResponseEntity<>(scheduleService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Schedule> findById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findByIdOrThrowBadRequestException(id), HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Schedule> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
