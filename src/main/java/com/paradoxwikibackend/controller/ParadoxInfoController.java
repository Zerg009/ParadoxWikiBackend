package com.paradoxwikibackend.controller;

import com.paradoxwikibackend.exception.DefaultResponse;
import com.paradoxwikibackend.exception.ParadoxNotFoundException;
import com.paradoxwikibackend.exception.UserNotAuthenticated;
import com.paradoxwikibackend.exception.UserNotFoundException;
import com.paradoxwikibackend.model.ParadoxInfo;
import com.paradoxwikibackend.model.UserHistory;
import com.paradoxwikibackend.repository.ParadoxInfoRepository;
import com.paradoxwikibackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.paradoxwikibackend.model.user.User;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ParadoxInfoController {
    private final ParadoxInfoRepository paradoxInfoRepository;

    private final UserService userService;

    @Autowired
    public ParadoxInfoController(ParadoxInfoRepository paradoxInfoRepository, UserService userService) {
        this.paradoxInfoRepository = paradoxInfoRepository;
        this.userService = userService;
    }

    @GetMapping("/paradox_info")
    public List<ParadoxInfo> getParadoxInfo() {
        return paradoxInfoRepository.findAll();
    }

    @PostMapping("/paradox_info")
    public ParadoxInfo createParadoxInfo(@RequestBody ParadoxInfo paradoxInfo) {
        return paradoxInfoRepository.save(paradoxInfo);
    }

    @PostMapping("/user/add_favorite/{paradoxId}")
    public ResponseEntity<DefaultResponse> addFavorite(@PathVariable Integer paradoxId) {
        User currentUser = getCurrentUser();
        return ResponseEntity.ok(userService.addFavoriteParadox(currentUser.getUserId(), paradoxId));
    }

    @DeleteMapping("/user/remove_favorite/{paradoxId}")
    public ResponseEntity<DefaultResponse> removeFavoriteParadox(@PathVariable Integer paradoxId) {
        User currentUser = getCurrentUser();
        return ResponseEntity.ok(userService.removeFavoriteParadox(currentUser.getUserId(), paradoxId));
    }

    @GetMapping("/user/favorites")
    public ResponseEntity<List<ParadoxInfo>> getFavoriteParadoxes() {
        User currentUser = getCurrentUser();
        List<ParadoxInfo> favoriteParadoxes = userService.getFavoriteParadoxes(currentUser.getUserId());
        return ResponseEntity.ok(favoriteParadoxes);
    }

    @GetMapping("/user/history")
    public ResponseEntity<List<UserHistory>> getUserHistory() {
        User currentUser = getCurrentUser();
        List<UserHistory> userHistory = userService.getUserHistory(currentUser.getUserId());
        return ResponseEntity.ok(userHistory);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthenticated("No authenticated user found");
        }
        return (User) authentication.getPrincipal();
    }
}