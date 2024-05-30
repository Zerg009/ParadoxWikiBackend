package com.paradoxwikibackend.model;

import com.paradoxwikibackend.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_favorites")
public class UserFavorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favorite_id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "paradox_id")
    private ParadoxInfo paradox;
}
