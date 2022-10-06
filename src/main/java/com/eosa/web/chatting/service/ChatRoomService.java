package com.eosa.web.chatting.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import com.eosa.web.chatting.repository.ChatRoomRepository;
import com.eosa.web.companys.entity.SelectAllCompanysList;
import com.eosa.web.companys.service.CompanysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.eosa.web.chatting.entity.ChatRoom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatRoomService implements ChatRoomRepository {

    private Map<String, ChatRoom> chatRooms;
    private Set<String> chatRoomIdList;
    
    @PostConstruct
    private void initChatRooms() {
        chatRooms = new LinkedHashMap<>();
    }

    @PostConstruct
    private void initChatRoomIdList() {
        chatRoomIdList = new HashSet<>();
    }

    public void testAllFlush() {
        chatRooms.clear();
        chatRoomIdList.clear();
    }    

    @Autowired private ChatRoomRepository chatRoomRepository;
    @Autowired private CompanysService companysService;

    /**
     * roomId가 일치하는 ChatRoom 찾기
     * @param roomId
     * @return
     */
    public ChatRoom findChatRoomByRoomId(String roomId) {
        return chatRooms.get(roomId);
    }

    public List<ChatRoom> getChatRoomListByUsersIdx(Long usersIdx) {
        List<ChatRoom> result = new ArrayList<>();
        Iterator<String> keys = chatRooms.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            ChatRoom c = chatRooms.get(key);
            if(c.getUsersIdx() == usersIdx) {
                result.add(c);
            }
        }
        return result;
    }

    /**
     * 채팅방 생성하기2
     * @param entity must not be {@literal null}.
     * @return
     * @param <S>
     */
    @Override
    public <S extends ChatRoom> S save(S entity) {
        if(entity.getCompanysIdx() == 0) {
            entity.setRoomId(UUID.randomUUID().toString());
            entity.setRoomName("상담사");
            entity.setDataInfo("");
            entity.setCreatedDate(LocalDateTime.now());
            entity.setUsable(1);
            chatRooms.put(entity.getRoomName(), entity);
            log.info("[save] insertRoomInfo: {}", entity.toString());
            return (S) chatRoomRepository.save(entity);
        } else {
            SelectAllCompanysList companys =  companysService.selectCompanysByCompanysIdx(entity.getCompanysIdx());
    //        log.debug("[save] companys: {}", companys.toString());
            String companysName = companys.getCompanysName();

            entity.setRoomId(UUID.randomUUID().toString());
            entity.setRoomName(companysName);
            entity.setDataInfo("");
            entity.setCreatedDate(LocalDateTime.now());
            entity.setUsable(1);
            chatRooms.put(entity.getRoomName(), entity);
            log.info("[save] insertRoomInfo: {}", entity.toString());
            return (S) chatRoomRepository.save(entity);
        }
    }

    @Override
    public int deleteRoomByRoomId(String roomId) {
        return chatRoomRepository.deleteRoomByRoomId(roomId);
    }

    public List<ChatRoom> selectRoomListOnServer(String roomId) {
        chatRooms.remove(roomId);
        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        return result;
    }

    /**
     * usersIdx가 일치하는 채팅방들을 List 형식으로 반환
     * @param usersIdx
     * @return
     */
    @Override
    public List<ChatRoom> selectChatRoomListByUsersIdx(Long usersIdx) {
        List<ChatRoom> result = chatRoomRepository.selectChatRoomListByUsersIdx(usersIdx);
        for(int i = 0; i < result.size(); i++) {
           chatRoomIdList.add(result.get(i).getRoomId());
        }
        Iterator<String> iter = chatRoomIdList.iterator();
        while(iter.hasNext()) {
            String crid = iter.next();
            log.debug("[selectChatRoomListByUsersIdx] iter: {}", crid);
            ChatRoom cr = selectChatRoomByChatRoomId(crid);
            chatRooms.put(crid, cr);
        }
        return result;
    }


    /**
     * companysIdx가 일치하는 채팅방들을 List로 출력
     * @param companysIdx
     * @return
     */
    @Override
    public List<ChatRoom> selectChatRoomListByCompanysIdx(Long companysIdx) {
        List<ChatRoom> result = chatRoomRepository.selectChatRoomListByCompanysIdx(companysIdx);
        for(int i = 0; i < result.size(); i++) {
            chatRooms.put(result.get(i).getRoomId(), result.get(i));
        }
        return result;
    }

    @Override
    public ChatRoom selectChatRoomByChatRoomId(String roomId) {
        return chatRoomRepository.selectChatRoomByChatRoomId(roomId);
    }

    /**
     * 모든 채팅방 목록보기(테스트용)
     * @return
     */
    public List<ChatRoom> findAllRoom() {
        // List<ChatRoom> result = new ArrayList<>(chatRooms.values()); 
        List<ChatRoom> result = null;
        List<ChatRoom> selectRows = chatRoomRepository.findAll();
        for(int i = 0; i < selectRows.size(); i++) {
            chatRooms.put(selectRows.get(i).getRoomId(), selectRows.get(i));
        }
        result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);
        return result;
    }
    
    public int setClientReadStatusUnread(String roomId) {
        ChatRoom c = null;
        Iterator<String> keys = chatRooms.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            c = chatRooms.get(key);
            if(c.getRoomId() == roomId) {
                c.setClientReadStatus(0);
            }
        }
        if(c != null) {
            return c.getClientReadStatus();
        }
        else {
            return 0;
        }
    }

    public int setDetectiveReadStatusUnread(String roomId) {
        ChatRoom c = null;
        Iterator<String> keys = chatRooms.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            c = chatRooms.get(key);
            if(c.getRoomId() == roomId) {
                c.setDetectiveReadStatus(0);
            }
        }
        return c.getDetectiveReadStatus();
    }

    public int setClientReadStatusRead(String roomId) {
        ChatRoom c = null;
        Iterator<String> keys = chatRooms.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            c = chatRooms.get(key);
            if(c.getRoomId() == roomId) {
                c.setClientReadStatus(1);
            }
        }
        return c.getClientReadStatus();
    }

    public int setDetectiveReadStatusRead(String roomId) {
        ChatRoom c = null;
        Iterator<String> keys = chatRooms.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            c = chatRooms.get(key);
            if(c.getRoomId() == roomId) {
                c.setDetectiveReadStatus(1);
            }
        }
        return c.getDetectiveReadStatus();
    }

    @Override
    public List<ChatRoom> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ChatRoom> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ChatRoom> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatRoom> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends ChatRoom> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatRoom> List<S> saveAllAndFlush(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<ChatRoom> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ChatRoom getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatRoom getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatRoom getReferenceById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatRoom> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatRoom> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<ChatRoom> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<ChatRoom> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(ChatRoom entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends ChatRoom> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends ChatRoom> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public <S extends ChatRoom> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends ChatRoom> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends ChatRoom> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <S extends ChatRoom, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        // TODO Auto-generated method stub
        return null;
    }

//    @Override
//    public int createChatRoom(ChatRoom chatRoom) {
//        // TODO Auto-generated method stub
//        return 0;
//    }


}
