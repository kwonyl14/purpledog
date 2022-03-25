package com.purpledog.younglin.user.dto.response;

import com.purpledog.younglin.user.entity.User;

/**
 * @FileName : UserUpdateRes
 * @Class 설명 : 회원 수정 API가 반환시킬 클래스
 */
public class UserUpdateRes {
    String id;
    String password;

    public static UserUpdateRes of (User user) {
        /**
         * @Method Name : of
         * @Method 설명 : User 엔티티를 UserUpdateRes로 변환시키는 정적 메소드
         */
        return new UserUpdateRes(user.getId(), user.getPassword());
    }

    public UserUpdateRes() {
    }

    public UserUpdateRes(String id, String password) {
        this.id = id;
        this.password = password;
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
