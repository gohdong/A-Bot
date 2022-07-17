package net.aibot.demo.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.aibot.demo.domain.dto.UserDirectoryDto;
import net.aibot.demo.util.IDGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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

    @Column(name = "is_file")
    private boolean isFile;

    @Builder
    public UserDirectory(long id, long parentId, String name, String description, boolean isFile) {
        setId(id);
        this.parentId = parentId;
        this.name = name;
        this.description = description;
        this.isFile = isFile;
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
                .isFile(isFile)
                .build();
    }
}
