package net.aibot.demo.listener;

import lombok.RequiredArgsConstructor;
import net.aibot.demo.component.ShareQueue;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;

@RequiredArgsConstructor
@Component
public class APIJobListener implements ApplicationListener<APIJobEvent> {
    private final ShareQueue shareQueue;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void onApplicationEvent(APIJobEvent event) {
        System.out.println("event@!@!@");
        sendToId(event.getId());
    }

    public void sendToId(String id) {
        String destination = "/receive/" + id;
        int tryTime = 0;
        boolean finalFlag = false;
        while (true) {
            ConcurrentLinkedQueue<String> thisisid = shareQueue.getEachQueue().get(id);
            if (thisisid != null) {
                while (!thisisid.isEmpty() && shareQueue.getClientAliveMap().getOrDefault(id, false)) {
                    tryTime++;
                    String pollItem = thisisid.poll();
                    if (pollItem.equals(shareQueue.END_OF_QUEUE)) {
                        finalFlag = true;
                        break;
                    }
                    System.out.println("try time ==== " + tryTime);
                    System.out.println("poll ==== " + pollItem);
                    simpMessagingTemplate.convertAndSend(destination, pollItem);
                    System.out.println("send done");
                }
            }
            if (finalFlag) break;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
