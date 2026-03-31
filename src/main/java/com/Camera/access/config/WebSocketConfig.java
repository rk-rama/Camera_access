package com.Camera.access.config;



import com.Camera.access.handler.VideoSignalingHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // WebSocket endpoint register kar rahe hain
        registry.addHandler(new VideoSignalingHandler(), "/video-stream")
                .setAllowedOrigins("*");
    }
}