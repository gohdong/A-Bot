package net.aibot.demo.controller;

import lombok.RequiredArgsConstructor;
import net.aibot.demo.component.ShareQueue;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.ConcurrentLinkedQueue;

@RequiredArgsConstructor
@Controller
public class TestWebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/{id}")
    public void test(@Payload String message, @DestinationVariable String id) {
        simpMessagingTemplate.convertAndSend("/receive/" + id, message);
    }
}
