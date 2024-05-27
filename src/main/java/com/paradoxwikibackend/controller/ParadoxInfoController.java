package com.paradoxwikibackend.controller;

import com.paradoxwikibackend.model.ParadoxInfo;
import com.paradoxwikibackend.repository.ParadoxInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ParadoxInfoController {
    @Autowired
    private ParadoxInfoRepository paradoxInfoRepository;
    @GetMapping("/paradox_info")
    public List<ParadoxInfo> getParadoxInfo(){
        return paradoxInfoRepository.findAll();
    }
    @PostMapping("/paradox_info")
    public ParadoxInfo createParadoxInfo(@RequestBody ParadoxInfo paradoxInfo){
        return paradoxInfoRepository.save(paradoxInfo);
    }
}
