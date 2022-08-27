package net.aibot.demo.listener;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class APIJobEvent extends ApplicationEvent {
    private String id;

    public APIJobEvent(Object source, String id) {
        super(source);
        this.id = id;
    }

    public APIJobEvent(Object source) {
        super(source);
    }

    public APIJobEvent(Object source, Clock clock) {
        super(source, clock);
    }

    public String getId() {
        return id;
    }
}
