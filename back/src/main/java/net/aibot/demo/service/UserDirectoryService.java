package net.aibot.demo.service;

import net.aibot.demo.domain.dto.UserDirectoryDto;
import net.aibot.demo.domain.entity.UserDirectory;
import net.aibot.demo.repository.UserDirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDirectoryService {
    private final UserDirectoryRepository userDirectoryRepository;

    @Autowired
    public UserDirectoryService(UserDirectoryRepository userDirectoryRepository) {
        this.userDirectoryRepository = userDirectoryRepository;
    }

    public List<UserDirectoryDto> getUserDirectories() {
        List<UserDirectory> directories = userDirectoryRepository.findAll();
        List<UserDirectoryDto> result =  new ArrayList<>();
        for (UserDirectory userDirectory : directories) {
            result.add(userDirectory.toDto());
        }
        return result;
    }

    public UserDirectoryDto getUserDirectory(long userDirectoryId) {
        return null;
    }

    public Long setUserDirectory(UserDirectoryDto userDirectoryDto) {
        UserDirectory savedEntity = userDirectoryRepository.save(userDirectoryDto.toEntity());
        return savedEntity.getId();
    }

    public Long updateUserDirectory(UserDirectoryDto userDirectoryDto) {
        userDirectoryRepository.getReferenceById(userDirectoryDto.getId());
        UserDirectory savedEntity = userDirectoryRepository.save(userDirectoryDto.toEntity());
        return savedEntity.getId();
    }

    public List<Long> updateUserDirectories(List<UserDirectoryDto> userDirectoryDtos) {
        return null;
    }
}
