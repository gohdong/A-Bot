package net.aibot.demo.repository;

import net.aibot.demo.domain.entity.CommonHttpHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonHttpHeaderRepository extends JpaRepository<CommonHttpHeader, Long> {
}
