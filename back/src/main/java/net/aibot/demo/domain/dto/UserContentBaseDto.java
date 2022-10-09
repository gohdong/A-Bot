package net.aibot.demo.domain.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.aibot.demo.domain.FileType;
import net.aibot.demo.domain.entity.UserContent;

@NoArgsConstructor
@Data
public class UserContentBaseDto {
    private long id;
    private FileType fileType;

    @Builder
    public UserContentBaseDto(long id, FileType fileType) {
        setId(id);
        this.fileType = fileType;
    }

    public UserContent toEntity() {
        return UserContent.builder()
                .id(id)
                .fileType(fileType)
                .build();
    }
}
