package net.aibot.demo.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import net.aibot.demo.domain.entity.UserDirectory;

@ApiModel
@Data
public class UserDirectoryDto {
    private long id;
    private long parentId;
    private String name;
    private String description;
    private Boolean isFile;

    @Builder
    public UserDirectoryDto(long id, long parentId, String name, String description, boolean isFile) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.description = description;
        this.isFile = isFile;
    }

    public UserDirectory toEntity() {
        return UserDirectory.builder()
                .id(id)
                .parentId(parentId)
                .name(name)
                .description(description)
                .isFile(isFile)
                .build();
    }
}
