package com.example.chatApplicationProject.controller;

import com.example.chatApplicationProject.model.StatusModel;
import com.example.chatApplicationProject.service.StatusService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @Autowired
    StatusService statusService;
    @PostMapping("/add-status")
    public ResponseEntity<StatusModel> addStatus(@RequestBody String statusData){
        StatusModel statusModel=setStatusData(statusData);
        StatusModel statusModel1 = statusService.addStatus(statusModel);
        return new ResponseEntity<>(statusModel1, HttpStatus.CREATED);
    }

    private StatusModel setStatusData(String statusData) {
        JSONObject json=new JSONObject(statusData);
        StatusModel statusModel=new StatusModel();
        String statusName = json.getString("statusName");
        String statusDescription = json.getString("statusDescription");
        statusModel.setStatusDescription(statusDescription);
        statusModel.setStatusName(statusName);
        return statusModel;
    }
}
