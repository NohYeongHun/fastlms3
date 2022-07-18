package com.zerobase.fastlms.banner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerInput {
    /**
     * alterText : Alter 텍스트
     * linkUrl : 배너 URL
     * imgRoute : 이미지 경로
     * name : 배너 명
     * sortType : 정렬 순서
     * visibility : 프론트 표시 여부
     */
    private Long id;
    private String alterText;
    private String linkUrl;
    private String imgRoute;
    private String name;
    private int sortType;
    private boolean visibility;

}

