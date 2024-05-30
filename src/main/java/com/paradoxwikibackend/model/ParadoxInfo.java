package com.paradoxwikibackend.model;

import com.paradoxwikibackend.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "paradox_info")
public class ParadoxInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paradox_id;
    @Column(name = "title", length = 50)
    private String title;
    @Column(name = "author", length = 50)
    private String author;
    @Column(name = "description")
    String description;
    @Column(name = "tech_name")
    String tech_name;
    @Column(name = "is_favorite")
    String is_favorite;

    public ParadoxInfo() {
    }

    // Relationship with users who have favorited this paradox
    @ManyToMany(mappedBy = "favoriteParadoxes")
    private List<User> favoritedByUsers;
}
