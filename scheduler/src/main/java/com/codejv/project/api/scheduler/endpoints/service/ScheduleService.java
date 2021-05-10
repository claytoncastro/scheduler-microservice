package com.codejv.project.api.scheduler.endpoints.service;

import com.codejv.project.api.core.model.Schedule;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {
    Schedule save(Schedule schedule);
    Iterable<Schedule> findAll(Pageable pageable);
    Schedule update(Schedule schedule);
    Schedule findByIdOrThrowBadRequestException(Long id);
    void delete(Long id);
}
