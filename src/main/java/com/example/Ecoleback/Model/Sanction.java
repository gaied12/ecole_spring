package com.example.Ecoleback.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Sanction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sanction_type_id", referencedColumnName = "id")
    private SancType type;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sons_id",nullable = true)
    private Son son ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SancType getType() {
        return type;
    }

    public void setType(SancType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }
}
