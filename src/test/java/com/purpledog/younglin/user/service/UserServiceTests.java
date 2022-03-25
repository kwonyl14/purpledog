package com.purpledog.younglin.user.service;

import com.purpledog.younglin.user.dto.request.UserCreateReq;
import com.purpledog.younglin.user.dto.request.UserUpdateReq;
import com.purpledog.younglin.user.dto.response.UserCreateRes;
import com.purpledog.younglin.user.dto.response.UserUpdateRes;
import com.purpledog.younglin.user.entity.User;
import com.purpledog.younglin.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @FileName : UserServiceTests
 * @Class 설명 : 유저 서비스 기능 단위 테스트
 */

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
        String id = "id";
        String password = "password";
        UserCreateReq userCreateReq = new UserCreateReq(id, password);
        User user = userCreateReq.toEntity();
        when(userRepository.save(any(User.class))).thenReturn(user);

        //when
        UserCreateRes userCreateRes = userService.createUser(userCreateReq);

        //then
        assertThat(userCreateRes.getId()).isEqualTo(id);
        assertThat(userCreateRes.getPassword()).isEqualTo(password);
    }

    @Test
    @DisplayName("유저 비밀번호 변경 테스트")
    void updateUserPassword() {
        //given
        String id = "id";
        String password = "password";
        String updatedPassword = "updatedPassword";
        UserUpdateReq userUpdateReq = new UserUpdateReq(id, updatedPassword);
        User user = new User(id, password);
        when(userRepository.findById(any(String.class))).thenReturn(java.util.Optional.ofNullable(user));

        //when
        UserUpdateRes userUpdateRes = userService.updateUserPassword(userUpdateReq);

        //then
        assertThat(userUpdateRes.getPassword()).isEqualTo(updatedPassword);
    }

    @Test
    @DisplayName("존재하지 않는 유저 비밀번호 변경 테스트")
    void updateNotExistUserPassword() {
        //given
        String id = "id";
        String updatedPassword = "updatedPassword";
        UserUpdateReq userUpdateReq = new UserUpdateReq(id, updatedPassword);
        when(userRepository.findById(any(String.class)).orElse(null)).thenReturn(null);

        //when
        UserUpdateRes userUpdateRes = userService.updateUserPassword(userUpdateReq);

        //then
        assertThat(userUpdateRes).isNull();
    }

    @Test
    @DisplayName("모든 회원 삭제 테스트")
    void deleteAllUser() {
        userService.deleteAllUser();
        verify(userRepository).deleteAll();
    }
}
