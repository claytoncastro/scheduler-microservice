package com.codejv.project.api.scheduler.endpoints.mapper;

import com.codejv.project.api.core.model.Schedule;
import com.codejv.project.api.scheduler.endpoints.requests.SchedulePostRequestBody;
import com.codejv.project.api.scheduler.endpoints.requests.SchedulePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class ScheduleMapper {
    public static final ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);
    public abstract Schedule toSchedule(SchedulePostRequestBody schedulePostRequestBody);
    public abstract Schedule toSchedule(SchedulePutRequestBody schedulePutRequestBody);
}
