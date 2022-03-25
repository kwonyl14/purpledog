package com.purpledog.younglin.user.service;

import com.purpledog.younglin.user.dto.request.UserCreateReq;
import com.purpledog.younglin.user.dto.request.UserUpdateReq;
import com.purpledog.younglin.user.dto.response.UserCreateRes;
import com.purpledog.younglin.user.dto.response.UserUpdateRes;
import com.purpledog.younglin.user.entity.User;
import com.purpledog.younglin.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @FileName : UserServiceImpl
 * @Class 설명 : 회원 비즈니스 로직 구현
 */

@Service
public class UserServiceImpl implements UserService{

    final private UserRepository userRepository;

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

    @Override
    @Transactional
    public UserUpdateRes updateUserPassword(UserUpdateReq userUpdateReq) {
        /**
         * @Method Name : updateUserPassword
         * @Method 설명 : 수정 될 회원 정보를 넘겨받아 DB에 저장 후 저장된 User
         * 엔티티를 userUpdateReq 객체로 변환해 리턴하는 함수
         */
        User user = userRepository.findById(userUpdateReq.getId()).orElse(null);
        // 해당 유저가 존재하지 않으면 null 리턴
        if (user == null) return null;

        user.setPassword(userUpdateReq.getPassword());
        return UserUpdateRes.of(user);
    }

    @Override
    public void deleteAllUser() {
        userRepository.deleteAll();
    }

    @Override
    public boolean deleteUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return false;

        userRepository.deleteById(id);
        return true;
    }


}
