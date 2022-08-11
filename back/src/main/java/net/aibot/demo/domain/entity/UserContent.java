package net.aibot.demo.domain.entity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.aibot.demo.domain.FileType;
import net.aibot.demo.domain.dto.UserContentBaseDto;
import net.aibot.demo.domain.dto.UserContentDocumentDto;
import net.aibot.demo.domain.dto.UserContentTaskFileDto;

import javax.persistence.*;
import java.util.Map;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_directory")
//@TypeDef(name = "json", typeClass = JsonStringType.class)
public class UserContent {
    @Id
    private long id;

//    @Type(type = "json")
//    @Column(columnDefinition = "json")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "file_type")
    private FileType fileType;

    @Builder
    public UserContent(long id, String description, FileType fileType) {
        this.id = id;
        this.description = description;
        this.fileType = fileType;
    }

    public UserContentBaseDto toDto() {
        return UserContentBaseDto.builder()
                .id(id)
                .fileType(fileType)
                .build();
    }

    public UserContentDocumentDto toDocumentDto() {
        return UserContentDocumentDto.documentBuilder()
                .id(id)
                .description(new Gson().fromJson(description, new TypeToken<String>() {}.getType()))
                .fileType(fileType)
                .build();
    }

    public UserContentTaskFileDto toTaskFileDto() {
        return UserContentTaskFileDto.taskFileBuilder()
                .id(id)
                .description(new Gson().fromJson(description, new TypeToken<Map<String, Object>>() {}.getType()))
                .fileType(fileType)
                .build();
    }
}
