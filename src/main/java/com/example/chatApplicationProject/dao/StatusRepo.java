package com.example.chatApplicationProject.dao;

import com.example.chatApplicationProject.model.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<StatusModel,Integer> {
}
