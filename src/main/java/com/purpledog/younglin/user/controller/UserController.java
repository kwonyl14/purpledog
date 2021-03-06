package com.purpledog.younglin.user.controller;

import com.purpledog.younglin.user.dto.request.UserCreateReq;
import com.purpledog.younglin.user.dto.request.UserUpdateReq;
import com.purpledog.younglin.user.dto.response.UserCreateRes;
import com.purpledog.younglin.user.dto.response.UserFindRes;
import com.purpledog.younglin.user.dto.response.UserUpdateRes;
import com.purpledog.younglin.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @FileName : UserController
 * @Class 설명 : 회원 관련 API 수신 기능을 구현한 컨트롤러
 */

@RestController
@CrossOrigin(value = {"*"}, maxAge = 6000)
@RequestMapping("/api")
public class UserController {

    final private UserService userService;
    final static private String NONEXISTENT = "This is a non-existent member.";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "모든 유저 조회", notes = "현재 DB에 존재하는 모든 회원 정보를 반환하는 API")
    @GetMapping("/user")
    public ResponseEntity<List<UserFindRes>> getAllUser() {
        /**
         * @Method Name : getAllUser
         * @Method 설명 : 현재 DB에 존재하는 모든 회원 정보를 반환하는 API
         */
        List<UserFindRes> allUser = userService.findAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @ApiOperation(value = "ID로 특정 유저 조회", notes = "전달받은 id에 해당하는 유저 정보 조회 API")
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(
            @PathVariable String id) {
        /**
         * @Method Name : getUserById
         * @Method 설명 : 전달받은 id에 해당하는 유저 정보 조회 API
         */
        UserFindRes userById = userService.findUserById(id);
        if (userById == null) return new ResponseEntity<>(NONEXISTENT, HttpStatus.OK);
        else return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @ApiOperation(value = "유저 등록", notes = "유저정보(id, password)를 전달받아 회원가입 기능을 하는 API")
    @PostMapping("/user")
    public ResponseEntity<UserCreateRes> join(
            @RequestBody UserCreateReq userCreateReq) {
        /**
         * @Method Name : join
         * @Method 설명 : 유저정보(id, password)를 전달받아 회원가입 기능을 하는 API
         */
        UserCreateRes userCreateRes = userService
                .createUser(userCreateReq);

        return new ResponseEntity<>(userCreateRes, HttpStatus.OK);
    }

    @ApiOperation(value = "유저 비밀번호 수정", notes = "id와 변경할 password로 회원비밀번호를 변경하는 API")
    @PatchMapping("/user")
    public ResponseEntity<?> changeUserPassword(
            @RequestBody UserUpdateReq userUpdateReq) {
        /**
         * @Method Name : changeUserPassword
         * @Method 설명 : 변경할 비밀번호가 포함된 회원정보를
         * 전달받아 회원가입 기능을 하는 API, 존재하지 않는 회원은
         * 실패 메시지를 반환
         */
        UserUpdateRes userUpdateRes = userService
                .updateUserPassword(userUpdateReq);

        if (userUpdateRes == null) return new ResponseEntity<>(NONEXISTENT, HttpStatus.OK);
        else return new ResponseEntity<>(userUpdateRes, HttpStatus.OK);
    }

    @ApiOperation(value = "모든 유저 삭제", notes = "현재 DB에 저장된 모든 회원데이터를 삭제하는 API")
    @DeleteMapping("/user")
    public ResponseEntity<String> deleteAllUser() {
        /**
         * @Method Name : deleteAllUser
         * @Method 설명 : 현재 DB에 저장된 모든 회원데이터를 삭제하는 API
         */
        userService.deleteAllUser();
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @ApiOperation(value = "ID로 특정 유저 정보 삭제", notes = "전달받은 id에 해당하는 유저 정보 삭제 API")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable String id) {
        /**
         * @Method Name : deleteUserById
         * @Method 설명 : 특정 회원 아이디를 조회해서 있으면 삭제 후 200코드 반환,
         * 없으면 실패 메시지를 반환하는 API
         */
        boolean isDeleted = userService.deleteUserById(id);
        if (isDeleted) return new ResponseEntity<>("Success", HttpStatus.OK);
        else return new ResponseEntity<>(NONEXISTENT, HttpStatus.OK);
    }
}
