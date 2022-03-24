package com.purpledog.younglin.user.repository;

import com.purpledog.younglin.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

/**
 * @FileName : UserRepositoryTests
 * @Class 설명 : 유저 레포지토리 테스트
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
        Assertions.assertThat(saveUser.getId()).isEqualTo("id");
        Assertions.assertThat(saveUser.getPassword()).isEqualTo("password");
    }

}
