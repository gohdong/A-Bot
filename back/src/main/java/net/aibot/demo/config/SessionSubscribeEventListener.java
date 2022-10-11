package net.aibot.demo.config;

import lombok.RequiredArgsConstructor;
import net.aibot.demo.component.ShareQueue;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class SessionSubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {
    private final ShareQueue shareQueue;

    @Override
    public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
        String destination = Objects.requireNonNull(headerAccessor.getDestination());
        shareQueue.setClientAlive(destination, true);
    }
}