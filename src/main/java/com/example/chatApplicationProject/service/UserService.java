package com.example.chatApplicationProject.service;


import com.example.chatApplicationProject.dao.UserRepo;
import com.example.chatApplicationProject.model.UserModel;
import org.apache.catalina.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public UserModel addUser(UserModel jsonData) {
        return userRepo.save(jsonData);
    }


    public JSONArray getUser(String userId) {
        JSONArray response=new JSONArray();
        if(null!=userId){
            List<UserModel> usersList=userRepo.getUserByUserId(Integer.valueOf(userId));
            for(UserModel users:usersList){
                JSONObject userObj=createResponse(users);
                response.put(userObj);
            }
        }
        else {
            List<UserModel> usersList = userRepo.getAllUsers();
            for (UserModel users : usersList) {
                JSONObject userObj = createResponse(users);
                response.put(userObj);
            }
        }
        return response;
    }

    private JSONObject createResponse(UserModel users) {
        JSONObject json=new JSONObject();
        json.put("username",users.getUsername());
        json.put("firstName",users.getFirstName());
        json.put("lastName",users.getLastName());
        json.put("age",users.getAge());
        json.put("email",users.getEmail());
        json.put("phoneNumber",users.getPhoneNumber());
        json.put("gender",users.getGender());
        json.put("createdDate",users.getCreatedDate());
        return json;
    }

    public void deleteUser(int userId) {
        userRepo.deleteUserByUserId(userId);
    }
}
