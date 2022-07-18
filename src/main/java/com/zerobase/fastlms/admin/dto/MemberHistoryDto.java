package com.zerobase.fastlms.admin.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberHistoryDto {
    private Long id;
    private String userId;
    private LocalDateTime curLogin;
    private String userIp;
    private String userAgent;
}
