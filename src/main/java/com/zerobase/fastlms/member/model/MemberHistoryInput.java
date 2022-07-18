package com.zerobase.fastlms.member.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class MemberHistoryInput {
    private String userIp;
    private LocalDateTime curLogin;
    private String userId;
    private String userAgent;
}
