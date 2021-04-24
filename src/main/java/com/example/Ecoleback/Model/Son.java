package com.example.Ecoleback.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sons")
public class Son {
    @Id
    @GenericGenerator(name = "sequence_son_id", strategy = "com.example.Ecoleback.Model.CustomIdentifierGenerator")
    @GeneratedValue(generator = "sequence_son_id")
    @Column(name = "id")
    private String id ;
    @Column(name = "firstName")
    private String firstName ;
    @Column(name = "lastName")
    private String lastName ;
    @Column(name = "birthday")
    private String dateBirth ;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="level_id")
    private Level level ;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Absence> absences;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Sanction> sanctions;


    @Column(name = "sexe")
    private String sexe ;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private imageUser imageUser ;

    public com.example.Ecoleback.Model.imageUser getImageUser() {
        return imageUser;
    }

    public void setImageUser(com.example.Ecoleback.Model.imageUser imageUser) {
        this.imageUser = imageUser;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }

    public User getUser() {
        return user;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne( fetch = FetchType.LAZY, optional = true,cascade = CascadeType.ALL)
    @JoinColumn(nullable = true ,name="user_id")

    private User user ;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }
}
