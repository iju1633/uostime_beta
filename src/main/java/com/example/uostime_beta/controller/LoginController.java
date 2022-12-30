package com.example.uostime_beta.controller;

import com.example.uostime_beta.domain.User;
import com.example.uostime_beta.dto.LoginDTO;
import com.example.uostime_beta.dto.UserDTO;
import com.example.uostime_beta.service.UserService;
import com.example.uostime_beta.util.SessionConst;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    //@ApiOperation(value = "홈화면", notes = "UOSTime 의 첫 화면입니다. 세션을 가져와 회원을 반환합니다. 프론트에선 반환된 데이터가 null 이면 로그인 페이지를, 아니면 로그인된 페이지를 보여줘야 합니다.")
    public ResponseEntity<UserDTO> home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User loginMember) {
        return ResponseEntity.ok().body(userService.home(loginMember));
    }

    @PostMapping("/login")
    //@ApiOperation(value = "로그인", notes = "아이디와 비밀번호로 로그인합니다.")
    public ResponseEntity<UserDTO> login(@Validated @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok().body(userService.login(loginDTO.getUid(), loginDTO.getPassword()));
    }
}
