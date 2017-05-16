package com.gmail.yuriikrat.friendtreasury.domain;

import android.util.Log;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by romm on 06.02.17.
 */
public class Payment {

    private Integer userFrom;
    private Integer[] usersTo;
    private BigDecimal amount;
    private Integer shallIPayForMyself;

    private String description;

    private Date date;

    public Payment() {
    }

    public Payment(Integer userFrom, Integer[] usersTo, BigDecimal amount, String description, Date date) {
        this.userFrom = userFrom;
        this.usersTo = usersTo;
        this.amount = amount;
        this.description = description;
        this.shallIPayForMyself = 1;
        this.date = date;
    }

    public Payment(Integer userFrom, Integer[] usersTo, BigDecimal amount, String description, Date date, Integer shallIPayForMyself) {
        this.userFrom = userFrom;
        this.usersTo = usersTo;
        this.amount = amount;
        this.shallIPayForMyself = shallIPayForMyself;
        this.description = description;
        this.date = date;
    }

    public void validate() {
        setShallIPayForMyself(getShallIPayForMyself());
    }

    public Integer getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(Integer userFrom) {
        this.userFrom = userFrom;
    }

    public Integer[] getUsersTo() {
        return usersTo;
    }

    public void setUsersTo(Integer[] usersTo) {
        this.usersTo = usersTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getShallIPayForMyself() {
        return shallIPayForMyself;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setShallIPayForMyself(Integer shallIPayForMyself) {
        if (shallIPayForMyself == 0 || shallIPayForMyself == 1) {
            this.shallIPayForMyself = shallIPayForMyself;
        } else {
            this.shallIPayForMyself = 1;
            Log.e("PaymentDTO", "Trying to set " + shallIPayForMyself + " to 'setShallIPayForMyself' field. Setting default value : " + this.shallIPayForMyself + ".");
        }
    }

    @Override
    public String toString() {
        return "(" + userFrom + " -> [" + Arrays.toString(usersTo) + "]) : " + amount;
    }
}
