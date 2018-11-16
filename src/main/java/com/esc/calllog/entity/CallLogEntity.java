package com.esc.calllog.entity;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="telephone_log")
public class CallLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String phone_number;
    @Column
    private String date_dialled;
    @Column
    private String date_received;
    @Column
    private Time time_dialled;
    @Column
    private Time time_received;
    @Column
    private String source;

    public CallLogEntity() {
    }

    public CallLogEntity(String name, String phone_number, String date_dialled, String date_received, Time time_dialled, Time time_received, String source) {
        this.name = name;
        this.phone_number = phone_number;
        this.date_dialled = date_dialled;
        this.date_received = date_received;
        this.time_dialled = time_dialled;
        this.time_received = time_received;
        this.source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDate_dialled() {
        return date_dialled;
    }

    public void setDate_dialled(String date_dialled) {
        this.date_dialled = date_dialled;
    }

    public String getDate_received() {
        return date_received;
    }

    public void setDate_received(String date_received) {
        this.date_received = date_received;
    }

    public Time getTime_dialled() {
        return time_dialled;
    }

    public void setTime_dialled(Time time_dialled) {
        this.time_dialled = time_dialled;
    }

    public Time getTime_received() {
        return time_received;
    }

    public void setTime_received(Time time_received) {
        this.time_received = time_received;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}

