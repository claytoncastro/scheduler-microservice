package com.codejv.project.api.scheduler.endpoints.requests.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class SchedulePostRequestBody {

    @NotBlank(message = "The field 'name' is mandatory")
    @Schema(description = "This is the name of the person who will make the medical consultation" ,
            example = "Jhon Mayer")
    private String name;

    @NotBlank(message = "The field 'email' is mandatory")
    @Schema(description = "This is the email of the person who will make the medical consultation",
            example = "jhon_mayer@hotmail.com")
    private String email;

    @Schema(description = "This is the telephoneNumber of the person who will make the medical consultation",
            example = "119885544")
    private Long telephoneNumber;

    @Schema(description = "This is the schedulerDate of the person who will make the medical consultation",
            example = "2021-05-18")
    @NotNull(message = "The field 'schedulerData' is mandatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate schedulerDate;

}
