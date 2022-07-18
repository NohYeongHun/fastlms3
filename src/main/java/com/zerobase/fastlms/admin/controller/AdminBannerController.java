package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.util.BannerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController extends BaseController {

    private final BannerService bannerService;

    @GetMapping("/admin/banner/set.do")
    public String setBanner(
            Model model,
            @RequestParam(name = "id",required = false, defaultValue = "0")
                    long id
    ) {
        model.addAttribute("banner", bannerService.getBannerById(id));
        return "/admin/banner/set";
    }

    @PostMapping("/admin/banner/set.do")
    public String setBanner(MultipartFile multipartFile, BannerInput parameter){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        if (multipartFile != null && !multipartFile.getOriginalFilename().isEmpty()) {
            try {
                File newFile = new File(BannerUtil.getImageUrl(multipartFile.getOriginalFilename(), uuid));
                FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(newFile));
            } catch (IOException e) {
                log.info(e.getMessage());
            }
            parameter.setImgRoute(BannerUtil.getImageUrl(multipartFile.getOriginalFilename(), uuid));
            bannerService.setBanner(parameter);
        } else {
            parameter.setImgRoute("");
            bannerService.setBanner(parameter);
        }

        return "redirect:/admin/banner/list.do";
    }

    @PostMapping("/admin/banner/delete.do")
    public String deleteBanner(BannerParam parameter) {
        bannerService.delBanner(parameter.getIdList());
        return "redirect:/admin/banner/list.do";
    }

    @GetMapping("/admin/banner/list.do")
    public String bannerList(Model model, BannerParam parameter) {
        long totalCount = 0;
        List<BannerDto> bannerDtoList = bannerService.bannerListAll();

        if (bannerDtoList != null && bannerDtoList.size() > 0) {
            totalCount = bannerDtoList.get(0).getTotalCount();
        }

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("bannerList", bannerDtoList);

        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(
                totalCount,
                parameter.getPageSize(),
                parameter.getPageIndex(),
                queryString
        );
        model.addAttribute("pager", pagerHtml);
        return "/admin/banner/list";
    }

    @GetMapping("/admin/banner/add.do")
    public String bannerAdd() {
        return "/admin/banner/add";
    }

    @PostMapping("/admin/banner/add.do")
    public String bannerAdd(MultipartFile multipartFile, BannerInput parameter) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        if (multipartFile != null && !multipartFile.getOriginalFilename().isEmpty()) {
            try {
                File newFile = new File(BannerUtil.getImageUrl(multipartFile.getOriginalFilename(), uuid));
                FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(newFile));
            } catch (IOException e) {
                log.info(e.getMessage());
            }
            parameter.setImgRoute(BannerUtil.getImageUrl(multipartFile.getOriginalFilename(), uuid));
            bannerService.addBanner(parameter);
        } else {
            parameter.setImgRoute("");
            bannerService.addBanner(parameter);
        }

        return "redirect:/admin/banner/list.do";
    }
}
