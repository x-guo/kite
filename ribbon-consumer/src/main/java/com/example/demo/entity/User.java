package com.example.demo.entity;


import lombok.Data;

/**
 * @author zhenyu.guo
 * @date 2018/3/22.
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer sex;
    private String idCard;
    private String phone;
}
