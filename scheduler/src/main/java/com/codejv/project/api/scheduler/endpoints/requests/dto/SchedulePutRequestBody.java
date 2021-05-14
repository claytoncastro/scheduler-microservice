package com.codejv.project.api.scheduler.endpoints.requests.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class SchedulePutRequestBody {

    private Long id;
    @NotBlank(message = "The field 'name' is mandatory")
    private String name;
    @NotBlank(message = "The field 'email' is mandatory")
    private String email;
    private Long telephoneNumber;
    @NotNull(message = "The field 'schedulerData' is mandatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate schedulerData;
}
