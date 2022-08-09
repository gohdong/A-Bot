package net.aibot.demo.service;

import net.aibot.demo.domain.dto.CommonHttpHeaderDto;
import net.aibot.demo.domain.dto.UserDirectoryDto;
import net.aibot.demo.domain.entity.CommonHttpHeader;
import net.aibot.demo.repository.CommonHttpHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonHttpHeaderService {
    private final CommonHttpHeaderRepository commonHttpHeaderRepository;

    @Autowired
    public CommonHttpHeaderService(CommonHttpHeaderRepository commonHttpHeaderRepository) {
        this.commonHttpHeaderRepository = commonHttpHeaderRepository;
    }

    public List<CommonHttpHeaderDto> getCommonHttpHeaders() {
        List<CommonHttpHeader> commonHttpHeaders = commonHttpHeaderRepository.findAll();
        List<CommonHttpHeaderDto> commonHttpHeaderDtos = new ArrayList<>();
        for (CommonHttpHeader commonHttpHeader : commonHttpHeaders) {
            commonHttpHeaderDtos.add(commonHttpHeader.toDto());
        }
        return commonHttpHeaderDtos;
    }
}
