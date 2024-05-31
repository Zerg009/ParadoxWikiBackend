package com.paradoxwikibackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.paradoxwikibackend.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "user_history")
public class UserHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int history_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "paradox_id")
    @JsonBackReference
    private ParadoxInfo paradox;

    private Date visitTimestamp;
}
