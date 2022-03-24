package com.purpledog.younglin.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @FileName : User
 * @Class 설명 : DB의 회원 테이블과 맵핑될 User 엔티티
 */

@Entity
public class User {

    @Id
    String id;
    String password;

    public User() {}

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
