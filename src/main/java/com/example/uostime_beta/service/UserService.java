package com.example.uostime_beta.service;

import com.example.uostime_beta.domain.User;
import com.example.uostime_beta.domain.UserRole;
import com.example.uostime_beta.dto.UserDTO;
import com.example.uostime_beta.repository.UserRepository;
import com.example.uostime_beta.util.Const;
import com.example.uostime_beta.util.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) User loginMember) {

        // 세션을 가져와 회원을 반환합니다. 프론트에선 반환된 데이터가 null 이면 로그인 페이지를, 아니면 로그인된 페이지를 보여줘야 합니다.
        return UserDTO.from(loginMember);
    }

    public UserDTO login(String uid, String password) {

        User loginMember = userRepository.findUserByUid(uid);
        log.info(String.valueOf(loginMember));

        // 아이디 존재하지 않는 경우
        if (loginMember == null) { // 받은 uid 로 회원이 존재하는 지 확인
            throw new IllegalArgumentException("아이디가 존재하지 않습니다.");
        }

        // 관리자/일반 테스트/일반 유저 로그인
        // TODO : 개발 완료되는 경우, 테스트 계정 삭제해야 함 (아이디 겹칠 수 있기 때문)
        if (loginMember.getUserRole().equals(UserRole.ADMINISTRATOR)) { // 관리자의 경우
            if (loginMember.getUid().equals(Const.TEST_ADMIN_UID)) { // 테스트 계정의 경우
                if (!password.equals(Const.TEST_PWD)) {

                    throw new IllegalArgumentException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
                }
            } else { // 테스트 계정이 아닌 경우
                if (!passwordEncoder.matches(password, loginMember.getPassword())) {
                    throw new IllegalArgumentException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
                }
            }
        } else { // 일반 회원의 경우
            if (loginMember.getUid().equals(Const.TEST_USER_UID)) { // 일반 사용자의 경우
                if (!password.equals(Const.TEST_PWD)) { // 테스트 계정의 경우
                    throw new IllegalArgumentException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
                }
            } else { // 테스트 계정이 아닌 경우
                if (!passwordEncoder.matches(password, loginMember.getPassword())) {
                    throw new IllegalArgumentException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
                }
            }
        }

        if (!loginMember.getEnabled()) {
            throw new AccessDeniedException("메일 인증을 완료해주세요.");
        }

        // 신규 세션 생성
        HttpSession session = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
        // 세션에 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        log.info("기존의 세션 반환 및 혹은 세션을 생성하였습니다.");
        log.info("해당 세션 : " + session);

        return UserDTO.from(loginMember);
    }
}
