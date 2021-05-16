package com.example.Ecoleback.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Level {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name = "name")
    private String name;

    public Integer getNumStud() {
        return numStud;
    }
    public void setNumStud(Integer numStud) {
        this.numStud = numStud;
    }
    @OneToMany
    @JsonIgnore
    private List<Son> sons ;
    @OneToMany( cascade = CascadeType.ALL)
    @JsonIgnore
    private List<NotifInfo> notes;
    @Column(name = "student_number")
    private  Integer numStud ;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "time_table_id", referencedColumnName = "id")
    private TimeTable timeTable;
    @ManyToMany
    @JoinTable(name = "users_level",
            joinColumns = @JoinColumn(name = "level_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name ="prof_id" , referencedColumnName = "id"))
    @JsonIgnore
    private Set<Prof> profs;


    public Set<Prof> getProfs() {
        return profs;
    }

    public void setProfs(Set<Prof> profs) {
        this.profs = profs;
    }

    public List<NotifInfo> getNotes() {
        return notes;
    }

    public void setNotes(List<NotifInfo> notes) {
        this.notes = notes;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public List<Son> getSons() {
        return sons;
    }

    public void setSons(List<Son> sons) {
        this.sons = sons;
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
}
