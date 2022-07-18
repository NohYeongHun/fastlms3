package com.zerobase.fastlms.banner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BannerDto {
    private Long id;
    private String alterText;
    private String linkUrl;
    private String imgRoute;
    private String name;

    private int sortType;
    private boolean visibility;

    private LocalDateTime registered;

    //추가컬럼
    long totalCount;
    long seq;
}
