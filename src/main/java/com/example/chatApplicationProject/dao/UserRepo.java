package com.example.chatApplicationProject.dao;

import com.example.chatApplicationProject.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<UserModel,Integer> {
    @Query(value = "Select * from user_model where username = :username and status_id = 1", nativeQuery = true)
    public List<UserModel> findByUsername(String username);

    @Query(value = "select * from user_model where user_id = :userId and status_id = 1", nativeQuery = true)
    public List<UserModel> getUserByUserId(int userId);

    @Query(value = "select * from user_model where status_id = 1", nativeQuery = true)
    public List<UserModel> getAllUsers();


    @Modifying
    @Transactional
    @Query(value = "update user_model set status_id = 2 where user_id = :userId", countQuery = "SELECT count(*) FROM user_model", nativeQuery = true)
    public void deleteUserByUserId(int userId);
}
