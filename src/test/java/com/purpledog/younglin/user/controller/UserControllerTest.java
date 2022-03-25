package com.purpledog.younglin.user.controller;

import com.google.gson.Gson;
import com.purpledog.younglin.user.dto.request.UserCreateReq;
import com.purpledog.younglin.user.dto.request.UserUpdateReq;
import com.purpledog.younglin.user.dto.response.UserCreateRes;
import com.purpledog.younglin.user.dto.response.UserFindRes;
import com.purpledog.younglin.user.dto.response.UserUpdateRes;
import com.purpledog.younglin.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @FileName : UserControllerTest
 * @Class 설명 : 유저 컨트롤러 기능 단위 테스트
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    final static private String NONEXISTENT = "This is a non-existent member.";

    @Test
    @DisplayName("모든 유저 조회 테스트")
    void getAllUser() throws Exception {
        //given
        List<UserFindRes> userList = new ArrayList<>();
        userList.add(new UserFindRes("id1", "pwd1"));
        userList.add(new UserFindRes("id2", "pwd2"));
        userList.add(new UserFindRes("id3", "pwd3"));
        given(userService.findAllUser()).willReturn(userList);
        Gson gson = new Gson();
        String response = gson.toJson(userList);

        //when, then
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    @DisplayName("특정 유저 ID로 조회 테스트")
    void getUserById() throws Exception {
        //given
        UserFindRes userFindRes = new UserFindRes("id1", "pwd1");
        given(userService.findUserById("id1")).willReturn(userFindRes);
        Gson gson = new Gson();
        String response = gson.toJson(userFindRes);

        //when, then
        mockMvc.perform(get("/api/user/{id}", "id1"))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    @DisplayName("존재하지 않는 유저 ID로 조회 테스트")
    void getNotExistUserById() throws Exception {
        //given
        given(userService.findUserById("id1")).willReturn(null);
        String response = NONEXISTENT;

        //when, then
        mockMvc.perform(get("/api/user/{id}", "id1"))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    @DisplayName("회원가입 테스트")
    void join() throws Exception {
        //given
        UserCreateReq userCreateReq = new UserCreateReq("id", "pwd");
        UserCreateRes userCreateRes = UserCreateRes.of(userCreateReq.toEntity());
        given(userService.createUser(any(UserCreateReq.class))).willReturn(userCreateRes);
        //json형태로 변환
        Gson gson = new Gson();
        String content = gson.toJson(userCreateReq);
        String response = gson.toJson(userCreateRes);

        //when, then
        mockMvc.perform(post("/api/user")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    @DisplayName("회원 비밀번호 변경 테스트")
    void changeUserPassword() throws Exception {
        //given
        String id = "id";
        String updatedPassword = "updatedPassword";
        UserUpdateReq userUpdateReq = new UserUpdateReq(id, updatedPassword);
        UserUpdateRes userUpdateRes = UserUpdateRes.of(userUpdateReq.toEntity());
        given(userService.updateUserPassword(any(UserUpdateReq.class))).willReturn(userUpdateRes);
        //json형태로 변환
        Gson gson = new Gson();
        String content = gson.toJson(userUpdateReq);
        String response = gson.toJson(userUpdateRes);

        //when, then
        mockMvc.perform(patch("/api/user")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    @DisplayName("존재하지 않는 회원 비밀번호 변경 테스트")
    void changeNotExistUserPassword() throws Exception {
        //given
        String notExistId = "notExistId";
        String updatedPassword = "updatedPassword";
        UserUpdateReq userUpdateReq = new UserUpdateReq(notExistId, updatedPassword);
        given(userService.updateUserPassword(any(UserUpdateReq.class))).willReturn(null);
        //json형태로 변환
        Gson gson = new Gson();
        String content = gson.toJson(userUpdateReq);

        //when, then
        mockMvc.perform(patch("/api/user")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(NONEXISTENT));
    }

    @Test
    @DisplayName("모든 회원 삭제 테스트")
    void deleteAllUser() throws Exception {
        mockMvc.perform(delete("/api/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));

        verify(userService).deleteAllUser();
    }

    @Test
    @DisplayName("특정 회원 ID로 삭제 테스트")
    void deleteUserById() throws Exception {
        //given
        String id = "id";
        given(userService.deleteUserById(any(String.class))).willReturn(true);

        //when, then
        mockMvc.perform(delete("/api/user/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));

        verify(userService).deleteUserById(any(String.class));
    }

    @Test
    @DisplayName("존재하지 않는 회원 ID로 삭제 테스트")
    void deleteNotExistUserById() throws Exception {
        //given
        String notExistId = "notExistId";
        given(userService.deleteUserById(any(String.class))).willReturn(false);

        //when, then
        mockMvc.perform(delete("/api/user/{id}", notExistId))
                .andExpect(status().isOk())
                .andExpect(content().string(NONEXISTENT));

        verify(userService).deleteUserById(any(String.class));
    }
}