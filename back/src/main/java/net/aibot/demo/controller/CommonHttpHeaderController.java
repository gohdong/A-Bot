package net.aibot.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aibot.demo.domain.dto.CommonHttpHeaderDto;
import net.aibot.demo.service.CommonHttpHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"자주 쓰이는 HTTP Header 관한 API"})
@RestController
public class CommonHttpHeaderController {
    private final CommonHttpHeaderService commonHttpHeaderService;

    @Autowired
    public CommonHttpHeaderController(CommonHttpHeaderService commonHttpHeaderService) {
        this.commonHttpHeaderService = commonHttpHeaderService;
    }

    @ApiOperation(value = "자주 쓰이는 HTTP Header들을 불러온다.")
    @GetMapping(value = "/commonHttpHeader", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<CommonHttpHeaderDto> getCommonHttpHeaders() {
        return commonHttpHeaderService.getCommonHttpHeaders();
    }
}
