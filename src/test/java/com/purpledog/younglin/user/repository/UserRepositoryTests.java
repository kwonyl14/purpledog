package com.purpledog.younglin.user.repository;

import com.purpledog.younglin.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

/**
 * @FileName : UserRepositoryTests
 * @Class 설명 : 유저 레포지토리 기능 단위 테스트
 */

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create"
})
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("유저등록 테스트")
    void insertUser() {
        User user = new User("id", "password");
        User saveUser = userRepository.save(user);
        assertThat(saveUser.getId()).isEqualTo("id");
        assertThat(saveUser.getPassword()).isEqualTo("password");
    }

    @Test
    @DisplayName("유저 비밀번호 변경 테스트")
    void updateUserPassword() {
        //given
        User user = new User("id", "password");
        User saveUser = userRepository.save(user);

        //when
        saveUser.setPassword("UpdatedPassword");

        //then
        User userById = userRepository.findById("id").orElse(null);
        assertThat(userById).isNotNull();
        assertThat(userById.getPassword()).isNotEqualTo("password");
        assertThat(userById.getPassword()).isEqualTo("UpdatedPassword");
    }

    @Test
    @DisplayName("존재하지 않는 유저 비밀번호 변경 테스트")
    void updateNotExistUserPassword() {
        //given
        User user = new User("id", "password");
        User saveUser = userRepository.save(user);

        //when
        saveUser.setPassword("UpdatedPassword");

        //then
        User userById = userRepository.findById("id3").orElse(null);
        assertThat(userById).isNull();
    }


}
