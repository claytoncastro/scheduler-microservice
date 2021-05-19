package com.codejv.project.api.scheduler.util;

import com.codejv.project.api.core.model.Schedule;

import java.time.LocalDate;

public class ScheduleCreator {

    public static Schedule createSchedule() {
        return Schedule.builder()
                .name("Jhon Mayer")
                .email("jhon_mayer@hotmail.com")
                .telephoneNumber(855887744L)
                .schedulerDate(LocalDate.now())
                .build();
    }

    public static Schedule createValidSchedule() {
        return Schedule.builder()
                .id(1L)
                .name("Jhon Mayer")
                .email("jhon_mayer123123@hotmail.com")
                .telephoneNumber(855887744L)
                .schedulerDate(LocalDate.now())
                .build();
    }

}
