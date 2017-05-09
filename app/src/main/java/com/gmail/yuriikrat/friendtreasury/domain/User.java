package com.gmail.yuriikrat.friendtreasury.domain;

import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by romm on 01.02.17.
 */

public class User {

    private Integer id;

    private String phone;

    private String username;

    private String password;

    @Override
    public String toString() {
        return "{ id : " + id + ", username : \"" + username + "\"}";
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User u = (User) obj;
            return Integer.compare(id, u.getId()) == 0;
        }
        return false;
    }

    public User() {
    }

    public User(String phone, String username, String password) {
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
