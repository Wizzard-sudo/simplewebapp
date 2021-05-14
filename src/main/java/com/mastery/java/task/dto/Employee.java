package com.mastery.java.task.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "data model of employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer employeeId;
    @NotBlank(message = "не долюно быть пустым")
    @Size(min = 3, message = "должно состоять хотя бы из 3 символов")
    private String firstName;
    @NotBlank(message = "не долюно быть пустым")
    @Size(min = 3, message = "должно состоять хотя бы из 3 символов")
    private String lastName;
    @NotNull(message = "не долюно быть пустым")
    @Digits(integer = 2, fraction = 0)
    private Integer departamentId;
    @NotBlank(message = "не долюно быть пустым")
    private String jobTitle;
    @NotNull(message = "не долюно быть пустым")
    private String gender;
    @NotNull(message = "не долюно быть пустым")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiParam("формат: yyyy-MM-dd")
    private Date dateOfBirth;

    @JsonIgnore
    @Transient
    private String dateOfBirthString;

    public void getDateString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.setDateOfBirthString(format.format(this.getDateOfBirth()));
    }
}
