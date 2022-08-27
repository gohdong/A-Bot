package net.aibot.demo.controller;

import lombok.RequiredArgsConstructor;
import net.aibot.demo.component.ShareQueue;
import net.aibot.demo.listener.APIJobEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentLinkedQueue;

@RequiredArgsConstructor
@RestController
public class APIJobController {
    private final ShareQueue shareQueue;
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping(value = "/test/queue")
    public String storeQueue(@RequestParam int amount, @RequestParam String id) throws InterruptedException {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        shareQueue.getEachQueue().put(id, queue);

        new Thread(() -> {
            publisher.publishEvent(new APIJobEvent(this, id));
        }).start();

        for (int i=1 ; i<=amount ; i++) {
            queue.offer(i + " : hello?");
            Thread.sleep(500);
        }
        queue.offer(shareQueue.END_OF_QUEUE);

        return "test";
    }
}
