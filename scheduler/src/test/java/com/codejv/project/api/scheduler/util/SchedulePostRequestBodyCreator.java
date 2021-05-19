package com.codejv.project.api.scheduler.util;

import com.codejv.project.api.scheduler.endpoints.requests.dto.SchedulePostRequestBody;

import java.time.LocalDate;

public class SchedulePostRequestBodyCreator {

    public static SchedulePostRequestBody createSchedulePostRequestBody() {
        return SchedulePostRequestBody.builder()
                .name("Jhon Mayer")
                .email("jhon_mayer123123@hotmail.com")
                .telephoneNumber(855887744L)
                .schedulerDate(LocalDate.now())
                .build();
    }

}
