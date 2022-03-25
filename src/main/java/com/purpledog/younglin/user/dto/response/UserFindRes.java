package com.purpledog.younglin.user.dto.response;

import com.purpledog.younglin.user.entity.User;

public class UserFindRes {
    String id;
    String password;

    public static UserFindRes of (User user) {
        /**
         * @Method Name : of
         * @Method 설명 : User 엔티티를 UserUpdateRes로 변환시키는 정적 메소드
         */
        return new UserFindRes(user.getId(), user.getPassword());
    }

    public UserFindRes(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public UserFindRes() {
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
