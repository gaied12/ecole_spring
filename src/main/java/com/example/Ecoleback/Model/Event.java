package com.example.Ecoleback.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Event {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name = "type")
    private String type ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "title")
    private String title ;
    @Column(name = "desc")
    private String description;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "file_id", referencedColumnName = "id",nullable = true)
    private File file ;
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "image_id", referencedColumnName = "id",nullable = true)
    private imageUser imageUser ;

    @Column(name = "date")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "Date_creation")
    private Date dateCreation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="level_id",nullable = true)
    private Level level ;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public com.example.Ecoleback.Model.imageUser getImageUser() {
        return imageUser;
    }

    public void setImageUser(com.example.Ecoleback.Model.imageUser imageUser) {
        this.imageUser = imageUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
