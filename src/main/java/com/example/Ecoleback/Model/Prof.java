package com.example.Ecoleback.Model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity

public class Prof extends  User {
    @ManyToMany
    @JoinTable(name = "profs_subject",
            joinColumns = @JoinColumn(name = "prof_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
   private Set<Subject> subject ;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_table_id", referencedColumnName = "id")

    private TimeTable timeTable ;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private Device device ;
    @ManyToMany
    private Set<Level> level ;

    public Set<Level> getLevel() {
        return level;
    }

    public void setLevel(Set<Level> level) {
        this.level = level;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Set<Subject> getSubject() {
        return subject;
    }

    public void setSubject(Set<Subject> subject) {
        this.subject = subject;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }
}
