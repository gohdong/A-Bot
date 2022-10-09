package net.aibot.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.aibot.demo.domain.dto.UserContentBaseDto;
import net.aibot.demo.domain.vo.UserDocumentContentUpdateVO;
import net.aibot.demo.domain.vo.UserTaskFileContentUpdateVO;
import net.aibot.demo.service.UserDirectoryContentService;
import net.aibot.demo.util.GroupNotNullValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"유저 파일 내용 API"})
@RestController
public class UserDirectoryContentController {
    private final UserDirectoryContentService userDirectoryService;

    @Autowired
    public UserDirectoryContentController(UserDirectoryContentService userDirectoryService) {
        this.userDirectoryService = userDirectoryService;
    }

//    @ApiOperation(value = "유저의 전체 파일내용을 가져온다.")
//    @GetMapping(value = "/userDirectoryContent", produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody List<UserContentBaseDto> getUserDirectories() {
//        return userDirectoryService.getUserDirectoryContents();
//    }

    @ApiOperation(value = "유저의 파일내용 하나를 가져온다.")
    @GetMapping(value = "/userDirectoryContent/{userDirectoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UserContentBaseDto getUserDirectoryContent(@PathVariable long userDirectoryId) {
        return userDirectoryService.getUserDirectoryContent(userDirectoryId);
    }

//    @ApiOperation(value = "유저의 파일구조를 저장한다.")
//    @PostMapping(value = "/userDirectory", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody Long setUserDirectory(@RequestBody UserDirectoryUpdateVO userDirectoryUpdateVO) throws EmptyObjectException, ParentFileIsNotDirectoryException {
//        return userDirectoryService.setUserDirectory(userDirectoryUpdateVO.toDto());
//    }
//
    @ApiOperation(value = "유저의 Task 파일내용 하나를 변경한다.")
    @PutMapping(value = "/userDirectoryContent/taskFile/{userDirectoryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Long updateUserDirectoryTaskFileContent(
            @PathVariable long userDirectoryId,
            @RequestBody UserTaskFileContentUpdateVO userDirectoryUpdateVO) {
        GroupNotNullValidator.validateGroupNotNull(userDirectoryUpdateVO);
        return userDirectoryService.updateUserContent(userDirectoryId, userDirectoryUpdateVO);
    }

    @ApiOperation(value = "유저의 Document 파일내용 하나를 변경한다.")
    @PutMapping(value = "/userDirectoryContent/document/{userDirectoryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Long updateUserDirectoryDocumentContent(
            @PathVariable long userDirectoryId,
            @RequestBody @Valid UserDocumentContentUpdateVO userDirectoryUpdateVO) {
        return userDirectoryService.updateUserContent(userDirectoryId, userDirectoryUpdateVO);
    }
}
