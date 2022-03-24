package com.purpledog.younglin.user.dto.request;

import com.purpledog.younglin.user.entity.User;

/**
 * @FileName : UserCreateReq
 * @Class 설명 : 클라이언트에서 유저 생성 API를 요청할 때
 * 유저정보를 담아 함께 전달할 클래스
 */
public class UserCreateReq {
    String id;
    String password;

    public UserCreateReq() {}

    public UserCreateReq(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public User toEntity() {
        /**
         * @Method Name : toEntity
         * @Method 설명 : 현재 객체에 담긴 데이터를 User 엔티티로
         * 변환해 반환하는 메소드
         */
        User user = new User(id, password);
        return user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
