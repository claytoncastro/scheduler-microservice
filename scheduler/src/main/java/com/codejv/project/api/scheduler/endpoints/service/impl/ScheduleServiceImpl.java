package com.codejv.project.api.scheduler.endpoints.service.impl;

import com.codejv.project.api.core.model.Schedule;
import com.codejv.project.api.core.repository.ScheduleRepository;
import com.codejv.project.api.scheduler.endpoints.requests.dto.SchedulePostRequestBody;
import com.codejv.project.api.scheduler.endpoints.requests.dto.SchedulePutRequestBody;
import com.codejv.project.api.scheduler.endpoints.requests.mapper.ScheduleMapper;
import com.codejv.project.api.scheduler.endpoints.service.ScheduleService;
import com.codejv.project.api.scheduler.exceptions.ResourceAlreadyExistException;
import com.codejv.project.api.scheduler.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@SuppressWarnings("java:S2201")
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule save(SchedulePostRequestBody schedulePostRequestBody) {
        isScheduleDateAvailable(schedulePostRequestBody.getSchedulerDate());
        log.info("Saving an schedule...");
        Schedule scheduleToSave = ScheduleMapper.INSTANCE.toSchedule(schedulePostRequestBody);
        return scheduleRepository.save(scheduleToSave);
    }

    @Override
    public Page<Schedule> findAll(Pageable pageable) {
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

    private void isScheduleDateAvailable(LocalDate schedulerDate) {
        Optional.ofNullable(scheduleRepository.findBySchedulerDate(schedulerDate))
                .filter(List::isEmpty)
                .orElseThrow(() -> new ResourceAlreadyExistException(
                        "Object with date " + schedulerDate + " already exist"));
    }

}
