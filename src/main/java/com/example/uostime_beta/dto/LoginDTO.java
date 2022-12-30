package com.example.uostime_beta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO { // 로그인 폼

    @NotBlank(message = "아이디를 기입해주세요.")
    private String uid;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
