package com.onlineshop.mapper;

import com.onlineshop.dto.request.*;
import com.onlineshop.dto.response.UserResponse;
import com.onlineshop.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser (UserCreationRequest request);

    @Mapping(target = "password", ignore = true)
    UserResponse toUserResponse (User user);

    void updateUser (@MappingTarget User user, UserUpdateRequest request);
}
