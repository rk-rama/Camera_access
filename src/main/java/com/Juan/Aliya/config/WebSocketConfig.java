package com.Juan.Aliya.config;

import com.Juan.Aliya.handler.VideoSignalingHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Endpoint setup aur CORS allow karna taaki phone connect ho sake
        registry.addHandler(new VideoSignalingHandler(), "/video-stream")
                .setAllowedOrigins("*");
    }
}