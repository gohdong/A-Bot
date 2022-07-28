package net.aibot.demo.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.aibot.demo.domain.FileType;
import net.aibot.demo.domain.dto.UserDirectoryDto;
import net.aibot.demo.util.IDGenerator;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Entity(name = "user_directory")
public class UserDirectory {
    @Id
    private long id;

    @Column(name = "parent_id")
    private long parentId;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "file_type")
    private FileType fileType;

    @Column(name = "created_date")
    private Date createdDate;


    @Builder
    public UserDirectory(long id, long parentId, String name, String description, FileType fileType, Date createdDate) {
        setId(id);
        this.parentId = parentId;
        this.name = name;
        this.description = description;
        this.fileType = fileType;
        this.createdDate = createdDate;
    }

    private void setId(long id) {
        if (id == 0L) {
            this.id = IDGenerator.makeRandomId();
        } else {
            this.id = id;
        }
    }

    public UserDirectoryDto toDto() {
        return UserDirectoryDto.builder()
                .id(id)
                .parentId(parentId)
                .name(name)
                .description(description)
                .fileType(fileType)
                .createdDate(createdDate)
                .build();
    }
}
