package com.purpledog.younglin.user.repository;

import com.purpledog.younglin.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
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
        User userById = userRepository.findById("id").orElse(null);

        //then
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
        User userById = userRepository.findById("id3").orElse(null);

        //then
        assertThat(userById).isNull();
    }

    @Test
    @DisplayName("모든 회원 삭제")
    void deleteAllUser() {
        //given
        User user1 = new User("id1", "password1");
        userRepository.save(user1);
        User user2 = new User("id2", "password2");
        userRepository.save(user2);
        User user3 = new User("id3", "password3");
        userRepository.save(user3);

        //when
        userRepository.deleteAll();
        List<User> all = userRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("특정 회원 ID로 삭제")
    void deleteUserById() {
        //given
        User user1 = new User("id1", "password1");
        userRepository.save(user1);
        User user2 = new User("id2", "password2");
        userRepository.save(user2);

        //when
        userRepository.deleteById("id1");
        User id1 = userRepository.findById("id1").orElse(null);
        User id2 = userRepository.findById("id2").orElse(null);
        //then
        assertThat(id1).isNull();
        assertThat(id2).isNotNull();
    }
}
