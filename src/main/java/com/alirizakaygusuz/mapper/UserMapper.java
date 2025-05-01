package com.alirizakaygusuz.mapper;

import org.mapstruct.Mapper;

import com.alirizakaygusuz.dto.DtoUser;
import com.alirizakaygusuz.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    DtoUser userToUserDto(User user);

}