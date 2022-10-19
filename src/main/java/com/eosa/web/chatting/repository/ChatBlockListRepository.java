package com.eosa.web.chatting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eosa.web.chatting.entity.ChatBlockList;

@Repository
public interface ChatBlockListRepository extends JpaRepository<ChatBlockList, Long> {
    
}
