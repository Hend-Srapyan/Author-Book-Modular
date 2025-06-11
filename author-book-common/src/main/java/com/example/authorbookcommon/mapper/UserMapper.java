package com.example.authorbookcommon.mapper;

import com.example.authorbookcommon.dto.SaveUserRequest;
import com.example.authorbookcommon.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SaveUserRequest saveUserRequest);
}