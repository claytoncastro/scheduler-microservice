package com.codejv.project.api.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "tb_schedule")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Schedule implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotBlank(message = "The field 'name' is mandatory")
    @Column(nullable = false)
    private String name;
    @NotBlank(message = "The field 'email' is mandatory")
    @Column(nullable = false)
    private String email;
    @Column(name = "telephone_number")
    private Long telephoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "scheduler_data")
    private LocalDate schedulerData;

}
