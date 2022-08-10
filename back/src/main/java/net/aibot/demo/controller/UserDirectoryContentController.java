package net.aibot.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aibot.demo.domain.dto.UserDirectoryContentDto;
import net.aibot.demo.domain.vo.UserDirectoryContentUpdateVO;
import net.aibot.demo.service.UserDirectoryContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"유저 파일 내용 API"})
@RestController
public class UserDirectoryContentController {
    private final UserDirectoryContentService userDirectoryService;

    @Autowired
    public UserDirectoryContentController(UserDirectoryContentService userDirectoryService) {
        this.userDirectoryService = userDirectoryService;
    }

    @ApiOperation(value = "유저의 전체 파일내용을 가져온다.")
//    @ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseDto.class)))
    @GetMapping(value = "/userDirectoryContent", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<UserDirectoryContentDto> getUserDirectories() {
        return userDirectoryService.getUserDirectoryContents();
    }

    @ApiOperation(value = "유저의 파일내용 하나를 가져온다.")
    @GetMapping(value = "/userDirectoryContent/{userDirectoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserDirectoryContentDto getUserDirectoryContent(@PathVariable long userDirectoryId) {
        return userDirectoryService.getUserDirectoryContent(userDirectoryId);
    }

//    @ApiOperation(value = "유저의 파일구조를 저장한다.")
//    @PostMapping(value = "/userDirectory", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody Long setUserDirectory(@RequestBody UserDirectoryUpdateVO userDirectoryUpdateVO) throws EmptyObjectException, ParentFileIsNotDirectoryException {
//        return userDirectoryService.setUserDirectory(userDirectoryUpdateVO.toDto());
//    }
//
    @ApiOperation(value = "유저의 파일내용 하나를 변경한다.")
    @PutMapping(value = "/userDirectoryContent/{userDirectoryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Long updateUserDirectory(
            @PathVariable long userDirectoryId,
            @RequestBody UserDirectoryContentUpdateVO userDirectoryUpdateVO) {
        return userDirectoryService.updateUserDirectory(userDirectoryUpdateVO.toDto(userDirectoryId));
    }

//    @ApiOperation(value = "유저의 파일구조 내용을 변경한다.")
//    @PutMapping(value = "/userDirectory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody List<Long> updateUserDirectories(@RequestBody List<UserDirectoryDto> userDirectoryDtos) {
//        return userDirectoryService.updateUserDirectories(userDirectoryDtos);
//    }
}
