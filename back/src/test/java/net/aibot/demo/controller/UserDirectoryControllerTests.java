package net.aibot.demo.controller;

import com.google.gson.Gson;
import net.aibot.demo.domain.FileType;
import net.aibot.demo.domain.vo.UserDirectoryUpdateVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class UserDirectoryControllerTests extends AbstractControllerTests{
    private final UserDirectoryController userDirectoryController;

    @Autowired
    public UserDirectoryControllerTests(UserDirectoryController userDirectoryController) {
        this.userDirectoryController = userDirectoryController;
    }

    @Override
    protected Object controller() {
        return userDirectoryController;
    }

    @Test
    public void getUserDirectories() throws Exception {
        mockMvc.perform(get("/userDirectory"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void getUserDirectoryByID_BAD_REQUEST() throws Exception {
        mockMvc.perform(get("/userDirectory/123"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    public void putUserDirectoryByID_BAD_REQUEST_ThereIsNoID() throws Exception {
        String json = new Gson().toJson(UserDirectoryUpdateVO.builder()
                .parentId(1)
                .name("name")
                .fileType(FileType.directory)
                .build());

        mockMvc.perform(put("/userDirectory/123")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.error").value("There is no ID"));
    }

    @Test
    public void deleteUserDirectoryByID_BAD_REQUEST() throws Exception {
        mockMvc.perform(delete("/userDirectory/123"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$").isMap());
    }
}

