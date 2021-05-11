package com.codejv.project.api.scheduler.endpoints.service;

import com.codejv.project.api.core.model.Schedule;
import com.codejv.project.api.scheduler.endpoints.requests.SchedulePostRequestBody;
import com.codejv.project.api.scheduler.endpoints.requests.SchedulePutRequestBody;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {
    Schedule save(SchedulePostRequestBody schedulePostRequestBody);
    Iterable<Schedule> findAll(Pageable pageable);
    Schedule update(SchedulePutRequestBody schedulePutRequestBody);
    Schedule findByIdOrThrowBadRequestException(Long id);
    void delete(Long id);
}
