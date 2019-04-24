package com.stone.redisson.bean;

import java.io.Serializable;

/**
 * @author honorstone
 * @date 2019/3/29 17:08
 */

public class Doctor implements Serializable {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
