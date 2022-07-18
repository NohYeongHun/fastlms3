package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.admin.dto.MemberHistoryDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.model.MemberHistoryInput;

import java.util.List;

public interface MemberHistoryService {


    boolean saveLoginHistory(MemberHistoryInput memberHistoryInput);

    List<MemberHistoryDto> memberLoginList(MemberParam memberParam);
}
