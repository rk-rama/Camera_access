package com.Camera.access.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/signaling")
public class SignalingController {

    /**
     * Check server status.
     * Admin dashboard iska use karke check kar sakta hai ki signaling server up hai ya nahi.
     */
    @GetMapping("/status")
    public Map<String, String> getStatus() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "ALIVE");
        status.put("message", "Signaling server is running on WebSockets");
        return status;
    }
}