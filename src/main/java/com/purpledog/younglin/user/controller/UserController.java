package com.purpledog.younglin.user.controller;

import com.purpledog.younglin.user.dto.request.UserCreateReq;
import com.purpledog.younglin.user.dto.request.UserUpdateReq;
import com.purpledog.younglin.user.dto.response.UserCreateRes;
import com.purpledog.younglin.user.dto.response.UserUpdateRes;
import com.purpledog.younglin.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @FileName : UserController
 * @Class 설명 : 회원 관련 API 수신 기능을 구현한 컨트롤러
 */

@RestController
@CrossOrigin(value = {"*"}, maxAge = 6000)
@RequestMapping("/api")
public class UserController {

    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserCreateRes> join(
            @RequestBody UserCreateReq userCreateReq) {
        /**
         * @Method Name : join
         * @Method 설명 : 회원정보를 전달받아 회원가입 기능을 하는 API
         */
        UserCreateRes userCreateRes = userService
                .createUser(userCreateReq);

        return new ResponseEntity<>(userCreateRes, HttpStatus.OK);
    }

    @PatchMapping("/user")
    public ResponseEntity<UserUpdateRes> changeUserPassword(
            @RequestBody UserUpdateReq userUpdateReq) {
        /**
         * @Method Name : changeUserPassword
         * @Method 설명 : 변경할 비밀번호가 포함된 회원정보를
         * 전달받아 회원가입 기능을 하는 API
         */
        UserUpdateRes userUpdateRes = userService
                .updateUserPassword(userUpdateReq);

        if (userUpdateRes == null) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(userUpdateRes, HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteAllUser() {
        /**
         * @Method Name : deleteAllUser
         * @Method 설명 : 현재 DB에 저장된 모든 회원데이터를 삭제하는 API
         */
        userService.deleteAllUser();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
