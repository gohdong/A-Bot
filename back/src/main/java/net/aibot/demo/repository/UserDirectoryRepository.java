package net.aibot.demo.repository;

import net.aibot.demo.domain.dto.UserDirectoryDto;
import net.aibot.demo.domain.entity.UserDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDirectoryRepository extends JpaRepository<UserDirectory, Long> {
}
