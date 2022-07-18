package com.zerobase.fastlms.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
public class BannerUtil {
    public static final String IMAGE_DIRECTORY = "D:/Study/fastlms3/src/main/resources/static/image";

    public static String getImageUrl(String name, String uuid) {
        LocalDateTime now = LocalDateTime.now();
        String url = String.format("%s/%d/%02d/%02d/", IMAGE_DIRECTORY, now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        String[] directory = {
                String.format("%s/%d/", IMAGE_DIRECTORY, now.getYear()),
                String.format("%s/%d/%02d/", IMAGE_DIRECTORY, now.getYear(), now.getMonthValue()),
                String.format("%s/%d/%02d/%02d/", IMAGE_DIRECTORY, now.getYear(), now.getMonthValue(), now.getDayOfMonth())};

        for (String dir : directory) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }

        String fileExtension = "";
        int dot = -1;
        dot = name.lastIndexOf(".");
        fileExtension = dot != -1 ? name.substring(dot + 1) : "";

        String destUrl = String.format("%s%s", url, uuid);

        return fileExtension.equals("") ? "" : destUrl + "." + fileExtension;
    }

}
