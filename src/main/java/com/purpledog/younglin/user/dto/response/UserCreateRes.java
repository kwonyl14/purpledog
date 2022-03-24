package com.purpledog.younglin.user.dto.response;

import com.purpledog.younglin.user.entity.User;

/**
 * @FileName : UserCreateRes
 * @Class 설명 : 회원 생성 API가 반환시킬 클래스
 */
public class UserCreateRes {
    String id;
    String password;

    public UserCreateRes(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public static UserCreateRes of (User user) {
        /**
         * @Method Name : of
         * @Method 설명 : User 엔티티를 UserCreateRes로 변환시키는 정적 메소드
         */
        return new UserCreateRes(user.getId(), user.getPassword());
    }
}
