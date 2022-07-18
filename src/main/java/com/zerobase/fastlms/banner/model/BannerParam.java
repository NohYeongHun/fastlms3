package com.zerobase.fastlms.banner.model;


import com.zerobase.fastlms.admin.model.CommonParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BannerParam extends CommonParam {
    String idList;
}
