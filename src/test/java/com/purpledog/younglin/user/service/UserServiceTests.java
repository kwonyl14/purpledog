package com.purpledog.younglin.user.service;

import com.purpledog.younglin.user.dto.request.UserCreateReq;
import com.purpledog.younglin.user.dto.request.UserUpdateReq;
import com.purpledog.younglin.user.dto.response.UserCreateRes;
import com.purpledog.younglin.user.dto.response.UserFindRes;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @DisplayName("모든 유저 조회 테스트")
    void findAllUser() {
        //given
        List<User> userList = new ArrayList<>();
        userList.add(new User("id1", "pwd1"));
        userList.add(new User("id2", "pwd2"));
        userList.add(new User("id3", "pwd3"));

        given(userRepository.findAll()).willReturn(userList);

        //when
        List<UserFindRes> allUser = userService.findAllUser();

        //then
        assertThat(allUser.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("특정 유저 ID로 조회 테스트")
    void findUserById() {
        //given
        User user1 = new User("id1", "pwd1");
        given(userRepository.findById("id1")).willReturn(Optional.of(user1));

        //when
        UserFindRes userById = userService.findUserById("id1");

        //then
        assertThat(userById).isNotNull();
    }

    @Test
    @DisplayName("존재하지 않는 유저 ID로 조회 테스트")
    void findNotExistUserById() {
        //given
        given(userRepository.findById("id1")).willReturn(Optional.empty());

        //when
        UserFindRes userById = userService.findUserById("id1");

        //then
        assertThat(userById).isNull();
    }

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

        //when
        when(userRepository.findById(any(String.class)))
                .thenReturn(Optional.empty());

        //then
        assertThat(userService.updateUserPassword(userUpdateReq)).isNull();
    }

    @Test
    @DisplayName("모든 회원 삭제 테스트")
    void deleteAllUser() {
        userService.deleteAllUser();
        verify(userRepository).deleteAll();
    }

    @Test
    @DisplayName("특정 회원 ID로 삭제 테스트")
    void deleteUserById() {
        //given
        String id = "id";
        String updatedPassword = "updatedPassword";
        UserUpdateReq userUpdateReq = new UserUpdateReq(id, updatedPassword);

        //when
        when(userRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(userUpdateReq.toEntity()));
        userService.deleteUserById("id");

        //then
        verify(userRepository).deleteById(any(String.class));
    }
}
