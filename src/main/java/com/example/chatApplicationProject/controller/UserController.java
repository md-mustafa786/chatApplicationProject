package com.example.chatApplicationProject.controller;

import com.example.chatApplicationProject.dao.StatusRepo;
import com.example.chatApplicationProject.dao.UserRepo;
import com.example.chatApplicationProject.model.StatusModel;
import com.example.chatApplicationProject.model.UserModel;
import com.example.chatApplicationProject.service.UserService;
import com.example.chatApplicationProject.utils.CommonUtils;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private StatusRepo statusRepo;

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody String userData){
        JSONObject isValid=validate(userData);
        if(isValid.isEmpty()){
            UserModel userModel = setUserData(userData);
            UserModel userModel1 = userService.addUser(userModel);
            return new ResponseEntity<>(userModel1.toString(),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(isValid.toString(),HttpStatus.BAD_REQUEST);
        }
    }

    private JSONObject validate(String userData) {
        JSONObject json = new JSONObject(userData);
        JSONObject error = new JSONObject();
        if (json.has("username")) {
            String username = json.getString("username");
            List<UserModel> byUsername = userRepo.findByUsername(username);
            if (!byUsername.isEmpty()) {
                error.put("username", "username already occupied");
                return error;
            }
        } else {
            error.append("username", "Missing parameter");
        }
        if (json.has("password")) {
            String password = json.getString("password");
            if (!CommonUtils.isValidPassword(password)) {
                error.append("password", "invalid password");
                return error;
            }
        } else {
            error.append("password", "Missing parameter");
        }
        if (json.has("email")) {
            String email = json.getString("email");
            if (!CommonUtils.isValidEmail(email)) {
                error.append("email", "invalid email");
                return error;
            }
        } else {
            error.append("email", "Missing parameter");
        }
        if (json.has("phoneNumber")) {
            String phoneNumber = json.getString("phoneNumber");
            if (!CommonUtils.isValidNumber(phoneNumber)) {
                error.append("phoneNumber", "invalid phoneNumber");
                return error;
            }
        } else {
            error.append("phoneNumber", "Missing parameter");
        }
        if (json.has("firstName")) {
            String firstName = json.getString("firstName");
            if (firstName.length() < 4 || firstName.length() > 20) {
                error.append("firstName", "please provide name in fields [4,20] size");
            }
        } else {
            error.append("firstName", "Missing parameter");
        }
        return error;
    }
    private UserModel setUserData(String userData){
        JSONObject json=new JSONObject(userData);
        UserModel userModel=new UserModel();
        userModel.setUsername(json.getString("username"));
        userModel.setFirstName(json.getString("firstName"));
        userModel.setEmail(json.getString("email"));
        userModel.setPhoneNumber(json.getString("phoneNumber"));
        userModel.setPassword(json.getString("password"));
        if(json.has("age")) {
            userModel.setAge(json.getInt("age"));
        }
        if(json.has("gender")) {
            userModel.setGender(json.getString("gender"));
        }
        if(json.has("lastName")){
            userModel.setLastName(json.getString("lastName"));
        }
        StatusModel statusModel=statusRepo.findById(1).get();
        userModel.setStatusId(statusModel);
        Timestamp createdDate=new Timestamp(System.currentTimeMillis());
        userModel.setCreatedDate(createdDate);
        return userModel;
    }
    @GetMapping("/get-user")
    public ResponseEntity<String> getOneUser(@Nullable @RequestBody String userId){
        JSONArray user = userService.getUser(userId);
        return new ResponseEntity<>(user.toString(),HttpStatus.FOUND);
    }
    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("userDeleted",HttpStatus.NO_CONTENT);
    }

}