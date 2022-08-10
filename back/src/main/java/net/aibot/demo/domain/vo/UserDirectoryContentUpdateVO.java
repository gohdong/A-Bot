package net.aibot.demo.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.aibot.demo.domain.FileType;
import net.aibot.demo.domain.dto.UserDirectoryContentDto;
import net.aibot.demo.domain.dto.UserDirectoryDto;

import java.util.Date;

@NoArgsConstructor
@ApiModel
@Data
public class UserDirectoryContentUpdateVO {
    private String description;

    @Builder
    public UserDirectoryContentUpdateVO(String description) {
        this.description = description;
    }

    public UserDirectoryContentDto toDto(Long id) {
        UserDirectoryContentDto userDirectoryDto = toDto();
        userDirectoryDto.setId(id);
        return userDirectoryDto;
    }

    public UserDirectoryContentDto toDto() {
        return UserDirectoryContentDto.builder()
                .description(description)
                .build();
    }
}
