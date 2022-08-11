package net.aibot.demo.domain.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.aibot.demo.domain.FileType;
import net.aibot.demo.domain.entity.UserContent;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserContentTaskFileDto extends UserContentBaseDto {
    private Map<String, Object> description;

    @Builder(builderMethodName = "taskFileBuilder")
    public UserContentTaskFileDto(long id, Map<String, Object> description, FileType fileType) {
        super(id, fileType);
        this.description = description;
    }

    @Override
    public UserContent toEntity() {
        return UserContent.builder()
                .id(getId())
                .description(new Gson().toJson(description))
                .fileType(getFileType())
                .build();
    }
}
