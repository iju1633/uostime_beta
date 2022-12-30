package com.example.uostime_beta.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 추후에 엔티티가 상속받아 편하게 사용할 수 있게 하기 위함
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    // 등록한 날짜, 마지막으로 수정한 날짜는 대부분의 엔티티에 필요하지만, 등록한 사람, 마지막으로 수정한 사람은 상황에 따라 필요하지 않을 수 있다.
    // 그래서 2개의 Base 타입을 분리하여 구현 (BaseTimeEntity, BaseEntity)

    @CreatedDate
    @Column(updatable = false) // 한 번 생성이 되고 나서는 수정되면 안된다
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
