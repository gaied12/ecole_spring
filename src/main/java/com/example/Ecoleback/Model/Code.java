package com.example.Ecoleback.Model;

import javax.persistence.*;

@Entity
public class Code {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @Column(name = "code")
    private Long code ;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "son_id", referencedColumnName = "id")
    private Son son;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }
}
