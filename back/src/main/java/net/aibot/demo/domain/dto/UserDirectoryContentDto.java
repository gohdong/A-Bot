package net.aibot.demo.domain.dto;

import com.google.gson.*;
import lombok.Builder;
import lombok.Data;
import net.aibot.demo.domain.entity.UserDirectory;
import net.aibot.demo.domain.entity.UserDirectoryContent;

import java.util.Map;

@Data
public class UserDirectoryContentDto {
    private long id;
    private String description;

    @Builder
    public UserDirectoryContentDto(long id, String description) {
        setId(id);
        this.description = description;
    }

    public UserDirectoryContent toEntity() {
        return UserDirectoryContent.builder()
                .id(id)
                .description(description)
                .build();
    }
}
