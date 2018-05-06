package com.why.dianpin.user.bean;

import org.json.JSONObject;

/**
 * @author shidefeng
 * @since 2018/5/6.
 */

public class UserBean {

    public int id;
    public int age;
    public int sex;
    public String username;
    public String password;

    public UserBean() {}

    public UserBean(int id, int age, int sex, String username, String password) {
        this.id = id;
        this.age = age;
        this.sex = sex;
        this.username = username;
        this.password = password;
    }

    public static UserBean fromJson(JSONObject json) {
        UserBean user = new UserBean();
        user.id = json.optInt("id");
        user.age = json.optInt("age");
        user.sex = json.optInt("sex");
        user.username = json.optString("username", "");
        user.password = json.optString("password", "");
        return user;
    }
}
