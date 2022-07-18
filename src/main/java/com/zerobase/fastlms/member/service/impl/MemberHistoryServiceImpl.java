package com.zerobase.fastlms.member.service.impl;

import com.zerobase.fastlms.admin.dto.MemberHistoryDto;
import com.zerobase.fastlms.admin.mapper.MemberHistoryMapper;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.entity.MemberHistory;
import com.zerobase.fastlms.member.model.MemberHistoryInput;
import com.zerobase.fastlms.member.repository.MemberHistoryRepository;
import com.zerobase.fastlms.member.service.MemberHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberHistoryServiceImpl implements MemberHistoryService {

    private final MemberHistoryRepository memberHistoryRepository;
    private final MemberHistoryMapper memberHistoryMapper;

    @Override
    public boolean saveLoginHistory(MemberHistoryInput memberHistoryInput) {

        try{
            memberHistoryRepository.save(
                    MemberHistory.builder()
                            .userId(memberHistoryInput.getUserId())
                            .curLogin(memberHistoryInput.getCurLogin())
                            .userIp(memberHistoryInput.getUserIp())
                            .userAgent(memberHistoryInput.getUserAgent())
                            .build());
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public List<MemberHistoryDto> memberLoginList(MemberParam memberParam) {
        return memberHistoryMapper.memberHistoryList(memberParam.getUserId());

    }
}
