package com.example.chatApplicationProject.controller;

import com.example.chatApplicationProject.dao.StatusRepo;
import com.example.chatApplicationProject.dao.UserRepo;
import com.example.chatApplicationProject.model.ChatHistory;
import com.example.chatApplicationProject.model.StatusModel;
import com.example.chatApplicationProject.model.UserModel;
import com.example.chatApplicationProject.service.ChatHistoryService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class ChatHistoryController {
    @Autowired
    private ChatHistoryService chatHistoryService;
    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private UserRepo userRepo;


    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody String messageData) {
        JSONObject errorCheck = validData(messageData);
        if (errorCheck.isEmpty()) {
            ChatHistory chatHistory = setData(messageData);
            ChatHistory chatHistory1 = chatHistoryService.saveMessage(chatHistory);
            return new ResponseEntity<>(chatHistory1.toString(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(errorCheck.toString(), HttpStatus.BAD_REQUEST);
    }

    private ChatHistory setData(String messageData) {
        ChatHistory chatHistory = new ChatHistory();
        JSONObject json = new JSONObject(messageData);
        String message = json.getString("message");
        int senderId = json.getInt("sender");
        int receiverId = json.getInt("receiver");
        UserModel senderUserObj = userRepo.findById(senderId).get();
        UserModel receiverUserObj = userRepo.findById(receiverId).get();
        chatHistory.setReceiver(receiverUserObj);
        chatHistory.setMessage(message);
        chatHistory.setSender(senderUserObj);
        StatusModel statusModel = statusRepo.findById(1).get();
        chatHistory.setStatusId(statusModel);
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        chatHistory.setCreatedDate(createdTime);
        return chatHistory;
    }

    private JSONObject validData(String messageData) {
        JSONObject json = new JSONObject(messageData);
        JSONObject error = new JSONObject();
        if (!json.has("sender")) {
            error.append("sender", "Missing parameter");
        }
        if (!json.has("receiver")) {
            error.append("receiver", "Missing parameter");
        }
        if (json.has("message")) {
            String message = json.getString("message");
            if (message.isBlank() || message.isEmpty()) {
                error.append("message", "message body cannot be emty");
            }
        } else {
            error.put("message", "Missing parameter");
        }
        return error;
    }

    @GetMapping(value = "/get-chat")
    public ResponseEntity<String> getChatsByUserId(@RequestParam int senderId) {
        JSONObject response = chatHistoryService.getChatsByUserId(senderId);
        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
    }

    @GetMapping("/get-conversation")
    public ResponseEntity<String> getConversationBetweenTwoUsers(@RequestParam int user1, @RequestParam int user2) {
        JSONObject response = chatHistoryService.getConversation(user1, user2);
        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
    }
}