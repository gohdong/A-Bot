package net.aibot.demo.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import net.aibot.demo.domain.FileType;
import net.aibot.demo.domain.entity.UserDirectory;

import java.util.Date;

@ApiModel
@Data
public class UserDirectoryDto {
    private long id;
    private long parentId;
    private String name;
    private String description;
    private FileType fileType;
    private Date createdDate;

    @Builder
    public UserDirectoryDto(long id, long parentId, String name, String description, FileType fileType, Date createdDate) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.description = description;
        this.fileType = fileType;
        this.createdDate = createdDate;
    }

    public UserDirectory toEntity() {
        return UserDirectory.builder()
                .id(id)
                .parentId(parentId)
                .name(name)
                .description(description)
                .fileType(fileType)
                .createdDate(createdDate)
                .build();
    }
}
