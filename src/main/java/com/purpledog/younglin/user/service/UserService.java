package com.purpledog.younglin.user.service;

import com.purpledog.younglin.user.dto.request.UserCreateReq;
import com.purpledog.younglin.user.dto.request.UserUpdateReq;
import com.purpledog.younglin.user.dto.response.UserCreateRes;
import com.purpledog.younglin.user.dto.response.UserFindRes;
import com.purpledog.younglin.user.dto.response.UserUpdateRes;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @FileName : UserService
 * @Class 설명 : 회원 서비스 인터페이스
 */

@Service
public interface UserService {
    List<UserFindRes> findAllUser();
    UserFindRes findUserById(String id);
    UserCreateRes createUser(UserCreateReq userCreateReq);
    UserUpdateRes updateUserPassword(UserUpdateReq userUpdateReq);
    void deleteAllUser();
    boolean deleteUserById(String id);
}
