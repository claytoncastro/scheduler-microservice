package com.codejv.project.api.scheduler.endpoints.service.impl;

import com.codejv.project.api.core.model.Schedule;
import com.codejv.project.api.core.repository.ScheduleRepository;
import com.codejv.project.api.scheduler.endpoints.service.ScheduleService;
import com.codejv.project.api.scheduler.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        findByIdOrThrowBadRequestException(schedule.getId());
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule findByIdOrThrowBadRequestException(Long id) {
        log.info("Finding an schedule by id: " + id);
        return scheduleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Object not found! Id: " + id + ", Type: " + Schedule.class.getName()));
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting an schedule by id: " + id);
        Long idToDelete = findByIdOrThrowBadRequestException(id).getId();
        scheduleRepository.deleteById(idToDelete);
    }

}
