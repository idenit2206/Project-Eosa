package com.eosa.web.chatting.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class CustomWebSocketConfig implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer {

    private final CustomWebSocketHandler customWebSocketHandler;

    /**
     *
     * From WebSocketConfigurer
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(customWebSocketHandler, "/ws/chat").setAllowedOriginPatterns("*");
//         registry.addHandler(new CustomUploadWSHandler(), "/binary");
    }

    /**
     * From WebSocketmessageBrokerConfigurer
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
            .addEndpoint("/ws/chat")
            .setAllowedOriginPatterns("*")
            .withSockJS();
    }

    /**
     * From WebSocketmessageBrokerConfigurer
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue", "/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    /**
     * 
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        // 전달되는 메시지 용량을 9MB로 제한한다.
        registration.setMessageSizeLimit(900 * 1024 * 1024);
    }

}