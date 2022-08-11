package net.aibot.demo.controller;

import com.google.gson.Gson;
import net.aibot.demo.domain.vo.UserTaskFileContentUpdateVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class UserContentControllerTests extends AbstractControllerTests{
    private final UserDirectoryContentController userDirectoryContentController;

    @Autowired
    public UserContentControllerTests(UserDirectoryContentController userDirectoryContentController) {
        this.userDirectoryContentController = userDirectoryContentController;
    }

    @Override
    protected Object controller() {
        return userDirectoryContentController;
    }

    @Test
    public void getUserDirectoryContents() throws Exception {
        mockMvc.perform(get("/userDirectoryContent"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void getUserDirectoryContentByID_BAD_REQUEST() throws Exception {
        mockMvc.perform(get("/userDirectoryContent/123"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.error").value("There is no ID"));
    }

    @Test
    public void putUserDirectoryContentTaskFileByID_BAD_REQUEST_ThereIsNoID() throws Exception {
        String json = new Gson().toJson(UserTaskFileContentUpdateVO.builder()
                .headers(new HashMap<>())
                .build());

        mockMvc.perform(put("/userDirectoryContent/taskFile/123")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.error").value("There is no ID"));
    }
}

