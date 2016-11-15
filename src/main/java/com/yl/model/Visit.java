package com.yl.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by JerryMouse on 2016/11/15.
 */
@Entity
@Table(name = "visit")
public class Visit implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nick")
    private String nick;

    @Column(name = "address")
    private String address;

    @Column(name = "joinTime",columnDefinition = "datetime")
    private Date joinTime;

    @Column(name = "uid")
    private String uid;

    @Column(name = "ip")
    private String ip;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
