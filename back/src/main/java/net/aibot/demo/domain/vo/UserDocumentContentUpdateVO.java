package net.aibot.demo.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

@NoArgsConstructor
@ApiModel
@Data
public class UserDocumentContentUpdateVO {
    @NotNull
    private String markdown;

    @Builder
    public UserDocumentContentUpdateVO(String markdown) {
        this.markdown = markdown;
    }
}
