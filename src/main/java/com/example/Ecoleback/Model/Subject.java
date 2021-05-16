package com.example.Ecoleback.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity

public class Subject {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @Column(name = "name")
    private String name ;
    @ManyToMany
    @JsonIgnore
    private Set<Prof> profs;

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

    public Set<Prof> getProfs() {
        return profs;
    }

    public void setProfs(Set<Prof> profs) {
        this.profs = profs;
    }
}
