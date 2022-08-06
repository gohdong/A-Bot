package net.aibot.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aibot.demo.domain.dto.UserDirectoryDto;
import net.aibot.demo.domain.vo.UserDirectoryUpdateVO;
import net.aibot.demo.exception.EmptyObjectException;
import net.aibot.demo.exception.ParentFileIsNotDirectoryException;
import net.aibot.demo.service.UserDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"유저 파일구조 API"})
@RestController
public class UserDirectoryController {
    private final UserDirectoryService userDirectoryService;

    @Autowired
    public UserDirectoryController(UserDirectoryService userDirectoryService) {
        this.userDirectoryService = userDirectoryService;
    }

    @ApiOperation(value = "유저의 전체 파일구조를 가져온다.")
//    @ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseDto.class)))
    @GetMapping(value = "/userDirectory", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<UserDirectoryDto> getUserDirectories() {
        return userDirectoryService.getUserDirectories();
    }

    @ApiOperation(value = "유저의 파일구조 하나를 가져온다.")
    @GetMapping(value = "/userDirectory/{userDirectoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserDirectoryDto getUserDirectory(@PathVariable long userDirectoryId) throws EmptyObjectException {
        return userDirectoryService.getUserDirectory(userDirectoryId);
    }

    @ApiOperation(value = "유저의 파일구조를 저장한다.")
    @PostMapping(value = "/userDirectory", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Long setUserDirectory(@RequestBody UserDirectoryUpdateVO userDirectoryUpdateVO) throws EmptyObjectException, ParentFileIsNotDirectoryException {
        return userDirectoryService.setUserDirectory(userDirectoryUpdateVO.toDto());
    }

    @ApiOperation(value = "유저의 파일구조 내용을 변경한다.")
    @PutMapping(value = "/userDirectory/{userDirectoryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Long updateUserDirectory(
            @PathVariable long userDirectoryId,
            @RequestBody UserDirectoryUpdateVO userDirectoryUpdateVO) throws EmptyObjectException {
        return userDirectoryService.updateUserDirectory(userDirectoryUpdateVO.toDto(userDirectoryId));
    }

//    @ApiOperation(value = "유저의 파일구조 내용을 변경한다.")
//    @PutMapping(value = "/userDirectory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody List<Long> updateUserDirectories(@RequestBody List<UserDirectoryDto> userDirectoryDtos) {
//        return userDirectoryService.updateUserDirectories(userDirectoryDtos);
//    }

    @ApiOperation(value = "유저의 파일구조 하나를 삭제한다.")
    @DeleteMapping(value = "/userDirectory/{userDirectoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Long deleteUserDirectory(
            @PathVariable long userDirectoryId) throws EmptyObjectException {
        return userDirectoryService.deleteUserDirectories(userDirectoryId);
    }
}
