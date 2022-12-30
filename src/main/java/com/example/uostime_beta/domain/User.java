package com.example.uostime_beta.domain;

import com.example.uostime_beta.converter.UserRoleConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@ToString(exclude = {"id", "password", "userRole", "enabled"})
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uid;
    private String nickName;
    private String portalID;
    private String password;

    @Convert(converter = UserRoleConverter.class)
    private UserRole userRole; // 확장성 대비

    private Boolean enabled = false; // 메일 인증 여부를 확인할 필드
}
