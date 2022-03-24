package com.purpledog.younglin.user.service;

import com.purpledog.younglin.user.dto.request.UserCreateReq;
import com.purpledog.younglin.user.dto.response.UserCreateRes;
import org.springframework.stereotype.Service;

/**
 * @FileName : UserService
 * @Class 설명 : 회원 서비스 인터페이스
 */

@Service
public interface UserService {
    UserCreateRes createUser(UserCreateReq userCreateReq);
}
