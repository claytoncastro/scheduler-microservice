package com.codejv.project.api.core.repository;

import com.codejv.project.api.core.model.Schedule;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {

    List<Schedule> findBySchedulerDate(LocalDate schedulerDate);

}
