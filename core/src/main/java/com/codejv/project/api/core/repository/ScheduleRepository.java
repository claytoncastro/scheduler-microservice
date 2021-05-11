package com.codejv.project.api.core.repository;

import com.codejv.project.api.core.model.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {

    @Query(value = "SELECT s.* FROM tb_schedule s WHERE DATE(scheduler_data)=:schedulerData", nativeQuery = true)
    List<Schedule> findBySchedulerData(@Param("schedulerData") LocalDate schedulerData);

}
