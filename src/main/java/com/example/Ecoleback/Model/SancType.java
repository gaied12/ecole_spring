package com.example.Ecoleback.Model;

import javax.persistence.*;

@Entity
public class SancType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @Enumerated(EnumType.STRING)
    private Sanc type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(Sanc type) {
        this.type = type;
    }

    public Sanc getType() {
        return type;
    }


}
