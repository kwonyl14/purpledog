package com.purpledog.younglin.user.repository;

import com.purpledog.younglin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @FileName : UserRepository
 * @Class 설명 : 유저 DB에 접근할 유저 JPA 레포지토리
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
