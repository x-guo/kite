package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhenyu.guo
 * @date 2018/3/30.
 */

@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String userName;
    private String cardNo;
    private Integer sex;
    private Boolean enable;
    private Integer age;

    public User(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

    public User(Long id, String userName, String cardNo, Integer sex, Boolean enable, Integer age) {
        this.id = id;
        this.userName = userName;
        this.cardNo = cardNo;
        this.sex = sex;
        this.enable = enable;
        this.age = age;
    }
}
