package com.codejv.project.api.scheduler.endpoints.service.impl;

import com.codejv.project.api.core.model.Schedule;
import com.codejv.project.api.core.repository.ScheduleRepository;
import com.codejv.project.api.scheduler.endpoints.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule save(Schedule schedule) {
        log.info("Saving an schedule...");
        return scheduleRepository.save(schedule);
    }

    @Override
    public Iterable<Schedule> findAll(Pageable pageable) {
        log.info("Listing all schedule...");
        return scheduleRepository.findAll(pageable);
    }

    @Override
    public Schedule update(Schedule schedule) {
        log.info("Updating an schedule...");
        Schedule scheduleToUpdate = findByIdOrThrowBadRequestException(schedule.getId());
        return scheduleRepository.save(scheduleToUpdate);
    }

    @Override
    public Schedule findByIdOrThrowBadRequestException(Long id) {
        log.info("Finding an schedule by id: " + id);
        return scheduleRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule not found"));
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting an schedule by id: " + id);
        Long idToDelete = findByIdOrThrowBadRequestException(id).getId();
        scheduleRepository.deleteById(idToDelete);
    }

}
