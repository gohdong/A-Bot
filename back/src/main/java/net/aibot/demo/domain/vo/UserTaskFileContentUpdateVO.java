package net.aibot.demo.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.aibot.demo.util.GroupNotNull;
import org.springframework.http.HttpMethod;

import java.util.Map;

@NoArgsConstructor
@ApiModel
@Data
public class UserTaskFileContentUpdateVO {
    @GroupNotNull(name = "userTaskFileContent")
    private Map<String, String> headers;

    @GroupNotNull(name = "userTaskFileContent")
    private Map<String, Object> body;

    @GroupNotNull(name = "userTaskFileContent")
    private String destinationURI;

    @GroupNotNull(name = "userTaskFileContent")
    private int amount;

    @GroupNotNull(name = "userTaskFileContent")
    private HttpMethod httpMethod;

    @Builder
    public UserTaskFileContentUpdateVO(Map<String, String> headers, Map<String, Object> body, String destinationURI, int amount, HttpMethod httpMethod) {
        this.headers = headers;
        this.body = body;
        this.destinationURI = destinationURI;
        this.amount = amount;
        this.httpMethod = httpMethod;
    }
}
