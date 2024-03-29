package com.zerobase.fastlms.admin.mapper;

import com.zerobase.fastlms.admin.dto.MemberHistoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberHistoryMapper {
    List<MemberHistoryDto> memberHistoryList(String userId);
}
