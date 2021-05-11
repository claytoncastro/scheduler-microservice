package com.codejv.project.api.scheduler.endpoints.service.impl;

import com.codejv.project.api.core.model.Schedule;
import com.codejv.project.api.core.repository.ScheduleRepository;
import com.codejv.project.api.scheduler.endpoints.mapper.ScheduleMapper;
import com.codejv.project.api.scheduler.endpoints.requests.SchedulePostRequestBody;
import com.codejv.project.api.scheduler.endpoints.requests.SchedulePutRequestBody;
import com.codejv.project.api.scheduler.endpoints.service.ScheduleService;
import com.codejv.project.api.scheduler.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule save(SchedulePostRequestBody schedulePostRequestBody) {
        if(!isDateAvailable(schedulePostRequestBody.getSchedulerData())) {
            throw new IllegalArgumentException("Schedule already registered!");
        }
        log.info("Saving an schedule...");
        Schedule scheduleToSave = ScheduleMapper.INSTANCE.toSchedule(schedulePostRequestBody);
        return scheduleRepository.save(scheduleToSave);
    }

    @Override
    public Iterable<Schedule> findAll(Pageable pageable) {
        log.info("Listing all schedule...");
        return scheduleRepository.findAll(pageable);
    }

    @Override
    public Schedule update(SchedulePutRequestBody schedulePutRequestBody) {
        log.info("Updating an schedule...");
        Schedule savedSchedule = findByIdOrThrowBadRequestException(schedulePutRequestBody.getId());
        Schedule schedule = ScheduleMapper.INSTANCE.toSchedule(schedulePutRequestBody);
        schedule.setId(savedSchedule.getId());
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
        Long idToDelete = findByIdOrThrowBadRequestException(id).getId();
        log.info("Deleting an schedule by id: " + id);
        scheduleRepository.deleteById(idToDelete);
    }

    private boolean isDateAvailable(LocalDate schedulerData) {
        return scheduleRepository.findBySchedulerData(schedulerData).isEmpty();
    }

}
