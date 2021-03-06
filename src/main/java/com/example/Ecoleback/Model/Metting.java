package com.example.Ecoleback.Model;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Metting {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private LocalTime time;




    @OneToOne
    @JoinColumn(name = "prof_id", referencedColumnName = "id")
    private Prof prof;

    @OneToOne
    @JoinColumn(name = "son_id", referencedColumnName = "id")
    private Son son;
    @OneToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Parent parent;




    @Column(name = "state")
    private MeetState state;

    public MeetState getState() {
        return state;
    }

    public void setState(MeetState state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
