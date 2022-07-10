package net.aibot.demo.controller;

import net.aibot.demo.controller.advice.CustomControllerExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractControllerTests {
    protected MockMvc mockMvc;

    abstract protected Object controller();

    @BeforeEach
    private void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller())
                //, new SuccessHandler()
                .setControllerAdvice(new CustomControllerExceptionHandler())
                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                .alwaysDo(print())
                .build();
    }

}
