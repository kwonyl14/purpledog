package com.purpledog.younglin.user.service;

import com.purpledog.younglin.user.dto.request.UserCreateReq;
import com.purpledog.younglin.user.dto.response.UserCreateRes;
import com.purpledog.younglin.user.entity.User;
import com.purpledog.younglin.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("유저 등록 테스트")
    void createUser() {
        //given
        UserCreateReq userCreateReq = new UserCreateReq("id", "pwd");
        User user = userCreateReq.toEntity();
        BDDMockito.when(userRepository.save(any(User.class))).thenReturn(user);

        //when
        UserCreateRes userCreateRes = userService.createUser(userCreateReq);

        //then
        Assertions.assertThat(userCreateRes.getId()).isEqualTo("id");
        Assertions.assertThat(userCreateRes.getPassword()).isEqualTo("pwd");
    }
}
