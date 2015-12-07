package com.spark.db;

import java.io.Serializable;

/**
 * Created by king on 2015/11/4.
 */
public class Person implements Serializable {

    private int id;
    private String pername;
    private String position;

    public Person(int id, String pername, String position) {
        this.id = id;
        this.pername = pername;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPername() {
        return pername;
    }

    public void setPername(String pername) {
        this.pername = pername;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
