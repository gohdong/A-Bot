package net.aibot.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                .andExpect(status().is(200))
                .andExpect(jsonPath("$").isArray());
    }

}

