package com.eosa.web.chatting.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.eosa.web.chatting.service.ChatBlockListService;
import com.eosa.web.chatting.service.ChatMessageService;
import com.eosa.web.chatting.service.ChatRoomService;
import com.eosa.web.companys.entity.SelectAllCompanysList;
import com.eosa.web.companys.service.CompanysService;
import com.eosa.web.firebase.pushnoti.service.FirebaseCloudMessage;
import com.eosa.web.users.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import com.eosa.web.chatting.entity.ChatBlockList;
import com.eosa.web.chatting.entity.ChatMessage;
import com.eosa.web.chatting.entity.ChatMessageParam;
import com.eosa.web.chatting.entity.ChatRoom;
import com.eosa.web.chatting.entity.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatMessageController {
    /*
    @MessageMapping()의 경로가 "/chat/message"이지만 ChatConfig의 setApplicationDestinationPrefixes()를 통해 
    prefix를 "/app"으로 해줬기 때문에 실질 경로는 "/app/chat/message"가 됨
    클라이언트에서 "/app/chat/message"의 경로로 메시지를 보내는 요청을 하면,
    메시지 Controller가 받아서 "topic/chat/room/{roomId}"를 구독하고 있는 클라이언트에게 메시지를 전달하게 됨.
    */
    private final SimpMessageSendingOperations sendingOperations;
    
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;
    @Autowired private ChatBlockListService chatBlockListService;
    @Autowired private UsersService usersService;
    @Autowired private CompanysService companysService;
    private final FirebaseCloudMessage firebaseCloudMessage;

    List<Object> messageList = new LinkedList<>();

    
    /** 
     * @param message
     */
    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessageParam message) {
        log.info("Message debug: {}", message.toString());
        String senderRole = message.getSender();
        ChatRoom roomInfo = chatRoomService.selectChatRoomByChatRoomId(message.getRoomId()); 
        SelectAllCompanysList c = companysService.selectCompanysByCompanysIdx(roomInfo.getCompanysIdx());
        Long clientIdx = roomInfo.getUsersIdx();
        Long companysCeoIdx = c.getCompanysCeoIdx(); 

        String clienttoken = usersService.getTokenByUsersIdx(clientIdx);
        String clientdevice = usersService.getDeviceByUsersIdx(clientIdx);

      
        String detectivetoken = usersService.getTokenByUsersIdx(companysCeoIdx);
        String detectivedevice = usersService.getDeviceByUsersIdx(companysCeoIdx);

        if((message.getMessageType()).equals(MessageType.ENTER)) {
            log.debug("sendMessage [ENTER]: {}", message.toString());
            message.setMessage(message.getSender() + "님이 입장했습니다.");
            // chatMessageService.addMessage(message);

            ChatMessage entity = new ChatMessage();
            entity.setMessageType(message.getMessageType());
            entity.setRoomId(message.getRoomId());
            entity.setMessage(message.getMessage());
            entity.setSender(message.getSender());
            entity.setSendDate(message.getSendDate());

            chatMessageService.save(entity);
        }

        if((message.getMessageType()).equals(MessageType.TALK)) {
            // log.info("sendMessage [TALK]: {}", message.toString());
            // log.info("sendMessage[TALK] message.roomId: {}", message.getRoomId());
            // chatMessageService.addMessage(message);
            if(senderRole.equals("client")) {
                // CLIENT가 메시지를 보내는 경우
                chatRoomService.changeReadStatusUnreadFromClient(message.getRoomId());
                try {
                    firebaseCloudMessage.sendMessageTo(clienttoken, "새로운 채팅 메시지가 도착했습니다.", "채팅방: "+roomInfo.getRoomName(), "/", clientdevice);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ChatMessage entity = new ChatMessage();
                entity.setMessageType(message.getMessageType());
                entity.setRoomId(message.getRoomId());
                entity.setMessage(message.getMessage());
                entity.setSender(message.getSender());
                entity.setSendDate(message.getSendDate());

                chatMessageService.save(entity);
            }
            else if(senderRole.equals("companys")){
                // DETECTIVE가 메시지를 보내는 경우
                List<ChatBlockList> blockedList = chatBlockListService.selectChatBlockListByBlocked(companysCeoIdx);
                if(blockedList != null) {
                    log.info("{}", blockedList.get(0).toString());
                    log.info("clientIdx: {}, companysCeoIdx: {}", clientIdx, companysCeoIdx);
                    for(int i = 0; i < blockedList.size(); i++) {
                        if(blockedList.get(i).getUsersIdxBlocker().equals(clientIdx) && blockedList.get(i).getUsersIdxBlocked().equals(companysCeoIdx)) {
                            // companysCeoIdx 는 usersIdx에게 차단 당한 상태
                            log.info("companysCeoIdx: {} 는 usersIdx: {} 에 차단되었기 때문에 메시지를 전송하지 않습니다.", companysCeoIdx, clientIdx);
                            
                        }
                        else {
                            chatRoomService.changeReadStatusUnreadFromDetective(message.getRoomId());
                            try {
                                firebaseCloudMessage.sendMessageTo(detectivetoken, "새로운 채팅 메시지가 도착했습니다.", "채팅방: "+roomInfo.getRoomName(), "/", detectivedevice);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            ChatMessage entity = new ChatMessage();
                            entity.setMessageType(message.getMessageType());
                            entity.setRoomId(message.getRoomId());
                            entity.setMessage(message.getMessage());
                            entity.setSender(message.getSender());
                            entity.setSendDate(message.getSendDate());

                            chatMessageService.save(entity);
                        }
                    }

                }
                // chatRoomService.changeReadStatusUnreadFromDetective(message.getRoomId());
                // try {
                //     firebaseCloudMessage.sendMessageTo(detectivetoken, "새로운 채팅 메시지가 도착했습니다.", "채팅방: "+roomInfo.getRoomName(), "/", detectivedevice);
                // } catch (IOException e) {
                //     // TODO Auto-generated catch block
                //     e.printStackTrace();
                // }
            }
            
            // ChatMessage entity = new ChatMessage();
            // entity.setMessageType(message.getMessageType());
            // entity.setRoomId(message.getRoomId());
            // entity.setMessage(message.getMessage());
            // entity.setSender(message.getSender());
            // entity.setSendDate(message.getSendDate());

            // chatMessageService.save(entity);
        }

        if((message.getMessageType()).equals(MessageType.FILE)) {
            // log.debug("sendMessage [FILE]: {}", message.toString());
            // chatMessageService.addMessage(message);
            
            ChatMessage entity = new ChatMessage();
            entity.setMessageType(message.getMessageType());
            entity.setRoomId(message.getRoomId());
            entity.setMessage(message.getMessage());
            entity.setSender(message.getSender());
            entity.setSendDate(message.getSendDate());

            chatMessageService.save(entity);
        }

        if((message.getMessageType()).equals(MessageType.LEAVE)) {
            message.setMessage(message.getSender() + "님이 퇴장했습니다.");
            log.debug("sendMessage [LEAVE]: {}", message.toString());
            // chatMessageService.addMessage(message);
            
            ChatMessage entity = new ChatMessage();
            entity.setMessageType(message.getMessageType());
            entity.setRoomId(message.getRoomId());
            entity.setMessage(message.getMessage());
            entity.setSender(message.getSender());
            entity.setSendDate(message.getSendDate());

            chatMessageService.save(entity);
        }    

        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }

    
    /** 
     * @return List<ChatMessage>
     */
    @GetMapping("/chat/message/read")
    public List<ChatMessage> readMessageList() {        
        return chatMessageService.readMessagesList();
    }


/** 
 * 채팅방 입장후 roomId 채팅방의 모든 메시지를 받아옵니다.
 * @return List<ChatMessage>
*/
//    @GetMapping("/chat/message/selectChatMessageByRoomId/{roomId}")
//    public List<ChatMessage> selectChatMessageByRoomId(@PathVariable String roomId) {
//        return chatMessageService.selectChatMessageByByRoomId(roomId);
//    }
    @GetMapping("/api/chat/message/selectChatMessageByRoomId")
    public List<ChatMessage> selectChatMessageByRoomId(
        @RequestParam("roomId") String roomId,
        @RequestParam("usersIdx") String usersIdx
    ) {        
        // log.info("roomId: {} , usersIdx: {}", roomId, usersIdx);
        Long parseLongUsersIdx = Long.parseLong(usersIdx);
        chatRoomService.changeReadStatus(roomId, parseLongUsersIdx);
        return chatMessageService.selectChatMessageByRoomId(roomId);
    }

}
