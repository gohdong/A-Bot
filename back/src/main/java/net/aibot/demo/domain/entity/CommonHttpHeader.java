package net.aibot.demo.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.aibot.demo.domain.dto.CommonHttpHeaderDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity(name = "common_http_header")
public class CommonHttpHeader {
    @Id
    private long id;
    private String name;

    @Builder
    public CommonHttpHeader(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CommonHttpHeaderDto toDto() {
        return CommonHttpHeaderDto.builder()
                .id(id)
                .name(name)
                .build();
    }
}
