package net.aibot.demo.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import net.aibot.demo.domain.dto.UserDirectoryDto;

@ApiModel
@Data
public class UserDirectoryUpdateVO {
    private long parentId;
    private String name;
    private String description;
    private Boolean isFile;

    @Builder
    public UserDirectoryUpdateVO(long parentId, String name, String description, boolean isFile) {
        this.parentId = parentId;
        this.name = name;
        this.description = description;
        this.isFile = isFile;
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
                .description(description)
                .isFile(isFile)
                .build();
    }
}
