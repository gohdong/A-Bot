package net.aibot.demo.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.aibot.demo.domain.FileType;
import net.aibot.demo.domain.entity.UserContent;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserContentDocumentDto extends UserContentBaseDto {
    private String description;

    @Builder(builderMethodName = "documentBuilder")
    public UserContentDocumentDto(long id, String description, FileType fileType) {
        super(id, fileType);
        this.description = description;
    }

    @Override
    public UserContent toEntity() {
        return UserContent.builder()
                .id(getId())
                .description(description)
                .fileType(getFileType())
                .build();
    }
}
