package net.aibot.demo.component;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

@Data
@Component
public class ShareQueue {
    public final String END_OF_QUEUE = "//ENDOFTEMPQUE";
    private Map<String, Boolean> clientAliveMap = new HashMap<>();
    private Map<String, ConcurrentLinkedQueue<String>> eachQueue = new HashMap<>();

    public void setClientAlive(String destination, boolean alive) {
        System.out.println("Destination " + destination);
        String[] split = destination.split("/");
        System.out.println();
        if (split.length == 3) {
            clientAliveMap.put(split[2], alive);
        }
    }
}
