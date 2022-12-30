package com.example.uostime_beta.repository;

import com.example.uostime_beta.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUid(String uid);

    boolean existsByNickName(String nickName);

    boolean existsByPortalID(String portalID);

    User findUserByUid(String uid);

    User findUserByPortalID(String portalID);
}
