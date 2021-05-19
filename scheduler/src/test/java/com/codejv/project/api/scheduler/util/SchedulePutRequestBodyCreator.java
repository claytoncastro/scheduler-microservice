package com.codejv.project.api.scheduler.util;

import com.codejv.project.api.scheduler.endpoints.requests.dto.SchedulePutRequestBody;

import java.time.LocalDate;

public class SchedulePutRequestBodyCreator {

    public static SchedulePutRequestBody createSchedulePutRequestBody() {
        return SchedulePutRequestBody.builder()
                .id(1L)
                .name("Jhon Mayer")
                .email("jhon_mayer@hotmail.com")
                .telephoneNumber(855887744L)
                .schedulerDate(LocalDate.now())
                .build();
    }

}
