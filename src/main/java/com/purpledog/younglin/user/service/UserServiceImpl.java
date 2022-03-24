package com.purpledog.younglin.user.service;

import com.purpledog.younglin.user.dto.request.UserCreateReq;
import com.purpledog.younglin.user.dto.response.UserCreateRes;
import com.purpledog.younglin.user.entity.User;
import com.purpledog.younglin.user.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * @FileName : UserServiceImpl
 * @Class 설명 : 회원 비즈니스 로직 구현
 */

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserCreateRes createUser(UserCreateReq userCreateReq) {
        /**
         * @Method Name : createUser
         * @Method 설명 : 회원 정보를 넘겨받아 DB에 저장 후 저장된 User
         * 엔티티를 UserCreateRes 객체로 변환해 리턴하는 함수
         */
        User user = userRepository.save(userCreateReq.toEntity());
        return UserCreateRes.of(user);
    }
}
