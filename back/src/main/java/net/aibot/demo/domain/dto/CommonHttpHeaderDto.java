package net.aibot.demo.domain.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;

@Data
public class CommonHttpHeaderDto {
    private long id;
    private String name;

    @Builder
    public CommonHttpHeaderDto(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
