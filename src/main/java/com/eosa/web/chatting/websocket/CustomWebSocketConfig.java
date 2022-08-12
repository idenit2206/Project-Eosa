package com.eosa.web.chatting.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class CustomWebSocketConfig implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer {

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
     * From WebSocketConfigurer
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // registry.addHandler(new CustomUploadWSHandler(), "/binary");
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