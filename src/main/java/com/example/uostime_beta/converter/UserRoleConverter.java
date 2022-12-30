package com.example.uostime_beta.converter;

/*
@Enumerated 사용할 수 있는데 converter 사용한 이유 :
- enum 의 값이 변경 되었을 경우나 order 순서가 변경되었을 때 DB에 저장된 값과 enum 의 값이 매칭되지 않아 문제가 발생할 수 있다.
- 반면에 converter 를 사용하면 변경된 값에 따른 별도의 로직 설정이 가능
 */

import com.example.uostime_beta.domain.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter // 해당 변환 클래스에 지정된 타입에 대해서는 모두 해당 변환 클래스의 메소드를 이용해 DB 와의 통신에서 값을 변환하려면 @Converter(autoApply = true) 옵션
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {

        switch (userRole) {
            case USER:
                return "user";
            case ADMINISTRATOR:
                return "administrator";
            default:
                throw new IllegalArgumentException("UserRole [" + userRole + "] not supported");
        }
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "user":
                return UserRole.USER;
            case "administrator":
                return UserRole.ADMINISTRATOR;
            default:
                throw new IllegalArgumentException("UserRole [" + dbData + "] not supported");
        }
    }
}

