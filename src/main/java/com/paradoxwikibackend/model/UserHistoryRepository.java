package com.paradoxwikibackend.model;

import com.paradoxwikibackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Integer> {
    //List<UserHistory> findByUserId(Integer userId);
    List<UserHistory> findByUser_UserId(Integer userId);
}
