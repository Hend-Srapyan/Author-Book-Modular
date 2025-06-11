package com.example.authorbookcommon.dto;

import com.example.authorbookcommon.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {

    private int id;
    private String name;
    private String surname;
    private String phone;
    private Gender gender;
}