package com.paradoxwikibackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "paradox_info")
public class ParadoxInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", length = 50)
    private String title;
    @Column(name = "author", length = 50)
    private String author;
    @Column(name = "description")
    String description;
    @Column(name = "tech_name")
    String tech_name;
    public ParadoxInfo() {
    }

    public ParadoxInfo(String tech_name, String description, String author, String title, long id) {
        this.tech_name = tech_name;
        this.description = description;
        this.author = author;
        this.title = title;
        this.id = id;
    }

    public String getTech_name() {
        return tech_name;
    }

    public void setTech_name(String tech_name) {
        this.tech_name = tech_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }
}
