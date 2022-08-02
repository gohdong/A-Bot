package net.aibot.demo.service;

import net.aibot.demo.domain.FileType;
import net.aibot.demo.domain.dto.UserDirectoryDto;
import net.aibot.demo.domain.entity.UserDirectory;
import net.aibot.demo.exception.EmptyObjectException;
import net.aibot.demo.exception.ParentFileIsNotDirectoryException;
import net.aibot.demo.repository.UserDirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public UserDirectoryDto getUserDirectory(long userDirectoryId) throws EmptyObjectException {
        Optional<UserDirectory> userDirectoryOptional = userDirectoryRepository.findById(userDirectoryId);
        if (userDirectoryOptional.isPresent()) {
            return userDirectoryOptional.get().toDto();
        }
        throw new EmptyObjectException();
    }

    public Long setUserDirectory(UserDirectoryDto userDirectoryDto) throws EmptyObjectException, ParentFileIsNotDirectoryException {
        isParentDirectory(userDirectoryDto);
        
        UserDirectory savedEntity = userDirectoryRepository.save(userDirectoryDto.toEntity());
        return savedEntity.getId();
    }

    private void isParentDirectory(UserDirectoryDto userDirectoryDto) throws EmptyObjectException, ParentFileIsNotDirectoryException{
        UserDirectoryDto parentDirectory = getUserDirectory(userDirectoryDto.getParentId());
        if (!parentDirectory.getFileType().equals(FileType.directory)) {
            throw new ParentFileIsNotDirectoryException();
        }
    }

    public Long updateUserDirectory(UserDirectoryDto userDirectoryDto) throws EmptyObjectException {
        Optional<UserDirectory> userDirectoryOptional = userDirectoryRepository.findById(userDirectoryDto.getId());
        if (userDirectoryOptional.isPresent()) {
            UserDirectory savedEntity = userDirectoryRepository.save(userDirectoryDto.toEntity());
            return savedEntity.getId();
        } else {
            throw new EmptyObjectException("There is no entity", HttpStatus.NO_CONTENT);
        }

    }

    public List<Long> updateUserDirectories(List<UserDirectoryDto> userDirectoryDtos) {


        return null;
    }

    public Long deleteUserDirectories(long userDirectoryId) throws EmptyObjectException {
        Optional<UserDirectory> userDirectoryOptional = userDirectoryRepository.findById(userDirectoryId);
        if (userDirectoryOptional.isPresent()) {
            userDirectoryRepository.delete(userDirectoryOptional.get());
            return userDirectoryId;
        } else {
            throw new EmptyObjectException("There is no entity", HttpStatus.NO_CONTENT);
        }
    }
}
