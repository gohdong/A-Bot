package net.aibot.demo.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import net.aibot.demo.domain.FileType;
import net.aibot.demo.domain.dto.UserDirectoryDto;

import java.util.Date;

@ApiModel
@Data
public class UserDirectoryUpdateVO {
    private long parentId;
    private String name;
    private FileType fileType;

    @Builder
    public UserDirectoryUpdateVO(long parentId, String name, String description, FileType fileType) {
        this.parentId = parentId;
        this.name = name;
//        this.description = description;
        this.fileType = fileType;
    }

    public UserDirectoryDto toDto(Long id) {
        UserDirectoryDto userDirectoryDto = toDto();
        userDirectoryDto.setId(id);
        return userDirectoryDto;
    }

    public UserDirectoryDto toDto() {
        return UserDirectoryDto.builder()
                .parentId(parentId)
                .name(name)
//                .description(description)
                .fileType(fileType)
                .createdDate(new Date())
                .build();
    }
}
