package com.zerobase.fastlms.banner.service.impl;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.BannerInfo;
import com.zerobase.fastlms.banner.mapper.BannerMapper;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.entity.Course;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public List<BannerDto> bannerList(BannerParam parameter) {
        Long totalCount = bannerMapper.bannerListCount();
        List<BannerDto> bannerDtoList = bannerMapper.bannerList(parameter);

        if (!CollectionUtils.isEmpty(bannerDtoList)) {
            int i = 0;
            for (BannerDto x : bannerDtoList) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return bannerDtoList;
    }

    @Override
    public List<BannerDto> bannerListAll() {
        return bannerMapper.bannerAllList();
    }

    @Override
    public boolean addBanner(BannerInput parameter) {

        bannerRepository.save(BannerInfo.builder()
                .id(parameter.getId())
                .alterText(parameter.getAlterText())
                .linkUrl(parameter.getLinkUrl())
                .name(parameter.getName())
                .imgRoute(parameter.getImgRoute())
                .sortType(parameter.getSortType())
                .visibility(parameter.isVisibility())
                .registered(LocalDateTime.now())
                .build());
        return true;
    }

    @Override
    public boolean setBanner(BannerInput parameter) {

        Optional<BannerInfo> optionalBannerInfo = bannerRepository.findById(parameter.getId());
        if (!optionalBannerInfo.isPresent()) {
            //수정할 데이터가 없음
            return false;
        }

        BannerInfo bannerInfo = optionalBannerInfo.get();
        bannerInfo.setId(parameter.getId());
        bannerInfo.setAlterText(parameter.getAlterText());
        bannerInfo.setImgRoute(parameter.getImgRoute());
        bannerInfo.setLinkUrl(parameter.getLinkUrl());
        bannerInfo.setName(parameter.getName());
        bannerInfo.setUpdated(LocalDateTime.now());

        bannerRepository.save(bannerInfo);

        return true;
    }

    @Override
    public boolean delBanner(String idList) {
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public BannerDto getBannerById(Long id) {
        Optional<BannerInfo> bannerOptional = bannerRepository.findById(id);

        if (!bannerOptional.isPresent()){
            return BannerDto.builder().build();
        }else{
            return BannerDto.builder()
                    .id(bannerOptional.get().getId())
                    .alterText(bannerOptional.get().getAlterText())
                    .linkUrl(bannerOptional.get().getLinkUrl())
                    .name(bannerOptional.get().getName())
                    .sortType(bannerOptional.get().getSortType())
                    .visibility(bannerOptional.get().isVisibility())
                    .registered(bannerOptional.get().getRegistered())
                    .build();
        }
    }
}
