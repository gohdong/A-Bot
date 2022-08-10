package net.aibot.demo.repository;

import net.aibot.demo.domain.entity.UserDirectory;
import net.aibot.demo.domain.entity.UserDirectoryContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDirectoryContentRepository extends JpaRepository<UserDirectoryContent, Long> {
}
