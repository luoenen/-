package com.luosenen.huelibrary.info;

import cn.bmob.v3.BmobObject;

public class Info extends BmobObject {

    private boolean isWork;
    private String seat;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    private String account;
    public boolean isWork() {
        return isWork;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public void setWork(boolean work) {
        isWork = work;
    }
}
