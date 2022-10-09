package net.aibot.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.aibot.demo.domain.FileType;
import net.aibot.demo.domain.dto.UserContentBaseDto;
import net.aibot.demo.domain.dto.UserContentDocumentDto;
import net.aibot.demo.domain.dto.UserContentTaskFileDto;
import net.aibot.demo.domain.entity.UserContent;
import net.aibot.demo.domain.vo.UserDocumentContentUpdateVO;
import net.aibot.demo.domain.vo.UserTaskFileContentUpdateVO;
import net.aibot.demo.repository.UserDirectoryContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserDirectoryContentService {
    private final UserDirectoryContentRepository userDirectoryContentRepository;

    @Autowired
    public UserDirectoryContentService(UserDirectoryContentRepository userDirectoryContentRepository) {
        this.userDirectoryContentRepository = userDirectoryContentRepository;
    }

    public List<UserContentBaseDto> getUserDirectoryContents() {
        List<UserContent> directories = userDirectoryContentRepository.findAll();
        List<UserContentBaseDto> result =  new ArrayList<>();
        for (UserContent userDirectory : directories) {
            UserContentBaseDto contentDto = makeContentDto(userDirectory);
            result.add(contentDto);
        }
        return result;
    }

    public UserContentBaseDto getUserDirectoryContent(long userDirectoryId) {
        Optional<UserContent> userDirectoryOptional = userDirectoryContentRepository.findById(userDirectoryId);
        if (userDirectoryOptional.isPresent()) {
            return makeContentDto(userDirectoryOptional.get());
        }
        throw new IllegalArgumentException("There is no ID");
    }

    private UserContentBaseDto makeContentDto(UserContent userDirectory) {
        if (userDirectory.getFileType().equals(FileType.task_file)) {
            return userDirectory.toTaskFileDto();
        } else {
            return userDirectory.toDocumentDto();
        }
    }

    @Transactional
    public <T> Long updateUserContent(long userDirectoryId, T userDirectoryUpdateVO) {
        Optional<UserContent> userDirectoryOptional = userDirectoryContentRepository.findById(userDirectoryId);
        if (userDirectoryOptional.isPresent()) {
            UserContentBaseDto previousDto = userDirectoryOptional.get().toDto();

            UserContentBaseDto userDirectoryContentDto = makeContentDtoWithInput(previousDto, userDirectoryUpdateVO);

            UserContent savedEntity = userDirectoryContentRepository.save(userDirectoryContentDto.toEntity());
            return savedEntity.getId();
        } else {
            throw new IllegalArgumentException("There is no ID");
        }
    }

    private <T> UserContentBaseDto makeContentDtoWithInput(UserContentBaseDto previousDto, T userDirectoryUpdateVO) {
        if (userDirectoryUpdateVO.getClass().equals(UserTaskFileContentUpdateVO.class)) {
            return UserContentTaskFileDto.taskFileBuilder()
                    .id(previousDto.getId())
                    .fileType(previousDto.getFileType())
                    .description(new ObjectMapper().convertValue(userDirectoryUpdateVO, new TypeReference<Map<String, Object>>() {}))
                    .build();

        } else {
            return UserContentDocumentDto.documentBuilder()
                    .id(previousDto.getId())
                    .fileType(previousDto.getFileType())
                    .description(((UserDocumentContentUpdateVO) userDirectoryUpdateVO).getMarkdown())
                    .build();
        }
    }
}
