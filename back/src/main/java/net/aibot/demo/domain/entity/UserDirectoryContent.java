package net.aibot.demo.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.aibot.demo.domain.FileType;
import net.aibot.demo.domain.dto.UserDirectoryContentDto;
import net.aibot.demo.domain.dto.UserDirectoryDto;
import net.aibot.demo.util.IDGenerator;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_directory")
//@TypeDef(name = "json", typeClass = JsonStringType.class)
public class UserDirectoryContent {
    @Id
    private long id;

//    @Type(type = "json")
//    @Column(columnDefinition = "json")
    private String description;

    @Builder
    public UserDirectoryContent(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public UserDirectoryContentDto toDto() {
        return UserDirectoryContentDto.builder()
                .id(id)
                .description(description)
                .build();
    }
}
