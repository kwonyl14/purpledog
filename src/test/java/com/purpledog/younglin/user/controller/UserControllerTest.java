package com.purpledog.younglin.user.controller;

import com.google.gson.Gson;
import com.purpledog.younglin.user.dto.request.UserCreateReq;
import com.purpledog.younglin.user.dto.response.UserCreateRes;
import com.purpledog.younglin.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

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
        String reponse = gson.toJson(userCreateRes);

        //when, then
        mockMvc.perform(post("/api/user")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(reponse));
    }
}