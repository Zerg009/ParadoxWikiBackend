package com.paradoxwikibackend.service;

import com.paradoxwikibackend.exception.DefaultResponse;
import com.paradoxwikibackend.exception.ParadoxNotFoundException;
import com.paradoxwikibackend.exception.UserNotFoundException;
import com.paradoxwikibackend.model.ParadoxInfo;
import com.paradoxwikibackend.model.UserHistory;
import com.paradoxwikibackend.model.UserHistoryRepository;
import com.paradoxwikibackend.model.user.User;
import com.paradoxwikibackend.model.user.UserRepository;
import com.paradoxwikibackend.repository.ParadoxInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ParadoxInfoRepository paradoxInfoRepository;

    private final UserHistoryRepository userHistoryRepository;

    @Autowired
    public UserService(UserRepository userRepository, ParadoxInfoRepository paradoxInfoRepository, UserHistoryRepository userHistoryRepository) {
        this.userRepository = userRepository;
        this.paradoxInfoRepository = paradoxInfoRepository;
        this.userHistoryRepository = userHistoryRepository;
    }



    @Transactional
    public DefaultResponse addFavoriteParadox(Integer userId, Integer paradoxId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<ParadoxInfo> paradoxOpt = paradoxInfoRepository.findById(paradoxId);

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        if (paradoxOpt.isEmpty()) {
            throw new ParadoxNotFoundException("Paradox not found");
        }

        User user = userOpt.get();
        ParadoxInfo paradox = paradoxOpt.get();

        user.getFavoriteParadoxes().add(paradox);
        userRepository.save(user);
        return new DefaultResponse("Paradox added to favorites successfully");
    }

    @Transactional(readOnly = true)
    public List<ParadoxInfo> getFavoriteParadoxes(Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return userOpt.get().getFavoriteParadoxes();
    }

    public List<UserHistory> getUserHistory(Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return userHistoryRepository.findByUser_UserId(userId);
    }
}
