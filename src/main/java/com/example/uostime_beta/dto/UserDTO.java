package com.example.uostime_beta.dto;

import com.example.uostime_beta.converter.UserRoleConverter;
import com.example.uostime_beta.domain.User;
import com.example.uostime_beta.domain.UserRole;
import jakarta.persistence.Convert;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {

    private String uid;
    private String nickName;
    private String portalID;

    @Convert(converter = UserRoleConverter.class)
    private UserRole userRole;

    public static UserDTO from(User user) {
        return UserDTO.builder()
                .uid(user.getUid())
                .nickName(user.getNickName())
                .portalID(user.getPortalID())
                .userRole(user.getUserRole())
                .build();
    }
}