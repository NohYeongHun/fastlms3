package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;

import java.util.List;

public interface BannerService {
    List<BannerDto> bannerList(BannerParam parameter);

    List<BannerDto> bannerListAll();

    boolean addBanner(BannerInput parameter);
    boolean setBanner(BannerInput parameter);

    boolean delBanner(String idList);

    BannerDto getBannerById(Long id);



}
