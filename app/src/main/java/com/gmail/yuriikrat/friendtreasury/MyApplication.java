package com.gmail.yuriikrat.friendtreasury;

import android.app.Application;

import java.util.List;

/**
 * Created by romm on 09.05.17.
 */

public class MyApplication extends Application {

    //    public final String URL = "http://afternoon-reaches-25317.herokuapp.com/";
    public final String URL = "http://10.0.2.2:8080/";

    private Integer id;
    private String username;

    private Integer anotherUserID;
    private List<Integer> usersPayFor;

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

    public Integer getAnotherUserID() {
        return anotherUserID;
    }

    public void setAnotherUserID(Integer anotherUserID) {
        this.anotherUserID = anotherUserID;
    }

    public List<Integer> getUsersPayFor() {
        return usersPayFor;
    }

    public void setUsersPayFor(List<Integer> usersPayFor) {
        this.usersPayFor = usersPayFor;
    }
}
