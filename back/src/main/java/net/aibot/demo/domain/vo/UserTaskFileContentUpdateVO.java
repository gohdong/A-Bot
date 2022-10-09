package net.aibot.demo.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.aibot.demo.util.GroupNotNull;

import java.util.Map;

@NoArgsConstructor
@ApiModel
@Data
public class UserTaskFileContentUpdateVO {
    @GroupNotNull(name = "userTaskFileContent")
    private Map<String, String> headers;

    @GroupNotNull(name = "userTaskFileContent")
    private Map<String, Object> body;

    @Builder
    public UserTaskFileContentUpdateVO(Map<String, String> headers, Map<String, Object> body) {
        this.headers = headers;
        this.body = body;
    }
}
