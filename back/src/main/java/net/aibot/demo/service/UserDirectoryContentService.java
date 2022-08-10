package net.aibot.demo.service;

import net.aibot.demo.domain.dto.UserDirectoryContentDto;
import net.aibot.demo.domain.entity.UserDirectory;
import net.aibot.demo.domain.entity.UserDirectoryContent;
import net.aibot.demo.exception.EmptyObjectException;
import net.aibot.demo.repository.UserDirectoryContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDirectoryContentService {
    private final UserDirectoryContentRepository userDirectoryContentRepository;

    @Autowired
    public UserDirectoryContentService(UserDirectoryContentRepository userDirectoryContentRepository) {
        this.userDirectoryContentRepository = userDirectoryContentRepository;
    }

    public List<UserDirectoryContentDto> getUserDirectoryContents() {
        List<UserDirectoryContent> directories = userDirectoryContentRepository.findAll();
        List<UserDirectoryContentDto> result =  new ArrayList<>();
        for (UserDirectoryContent userDirectory : directories) {
            result.add(userDirectory.toDto());
        }
        return result;
    }

    public UserDirectoryContentDto getUserDirectoryContent(long userDirectoryId) {
        Optional<UserDirectoryContent> userDirectoryOptional = userDirectoryContentRepository.findById(userDirectoryId);
        if (userDirectoryOptional.isPresent()) {
            return userDirectoryOptional.get().toDto();
        }
        throw new IllegalArgumentException("There is no entity");
    }

    public Long updateUserDirectory(UserDirectoryContentDto userDirectoryContentDto) {
        Optional<UserDirectoryContent> userDirectoryOptional = userDirectoryContentRepository.findById(userDirectoryContentDto.getId());
        if (userDirectoryOptional.isPresent()) {
            UserDirectoryContent savedEntity = userDirectoryContentRepository.save(userDirectoryContentDto.toEntity());
            return savedEntity.getId();
        } else {
            throw new IllegalArgumentException("There is no entity");
        }

    }
}
