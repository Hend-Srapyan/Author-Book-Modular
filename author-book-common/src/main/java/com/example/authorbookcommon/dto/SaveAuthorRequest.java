package com.example.authorbookcommon.dto;

import com.example.authorbookcommon.entity.Gender;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveAuthorRequest {

    private int id;
    private String name;
    private String surname;
    @Min(value = 3, message = "Phone should be >= 3")
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirthday;
    private Gender gender;
}