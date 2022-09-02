package net.aibot.demo.repository;

import net.aibot.demo.domain.entity.UserContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDirectoryContentRepository extends JpaRepository<UserContent, Long> {
}
