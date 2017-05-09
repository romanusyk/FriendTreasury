package com.gmail.yuriikrat.friendtreasury;

import android.app.Application;

/**
 * Created by romm on 09.05.17.
 */

public class MyApplication extends Application {

    private Integer id;
    private String username;

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

}
