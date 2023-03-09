package com.example.chatApplicationProject.service;

import com.example.chatApplicationProject.dao.StatusRepo;
import com.example.chatApplicationProject.model.StatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    @Autowired
    StatusRepo statusRepo;
    public StatusModel addStatus(StatusModel statusModel) {
        return statusRepo.save(statusModel);
    }
}
