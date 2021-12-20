package com.laplace.core.controller;

import com.laplace.core.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/16 9:57
 * @Info:
 * @Email:
 */
@RestController
@ResponseBody
@Component
@Slf4j
public class FileController {

    @Resource
    FileService service;

    @PostMapping("/uploadPic")
    public Object uploadPic(@RequestParam("image") MultipartFile multipartFile) {
        try {
            return service.uploadPic(multipartFile);
        } catch (Exception e) {
            log.error("uploadPic:error\n", e);
        }
        return 404;
    }

    @PostMapping("/uploadMusic")
    public Object uploadMusic(@RequestParam("music") MultipartFile multipartFile) {
        try {
            return service.uploadMusic(multipartFile);
        } catch (Exception e) {
            log.error("uploadMusic:error\n", e);
        }
        return 404;
    }

    @RequestMapping("/downloadPic/{bucket}/{fileName}")
    public Object downloadPic(@PathVariable("bucket") String bucket, @PathVariable("fileName") String fileName) {
        try {
            return service.downloadPic(URLDecoder.decode(bucket, "UTF-8"), URLDecoder.decode(fileName, "UTF-8"));
        } catch (Exception e) {
            log.error("downloadPic:error\n", e);
        }
        return 404;
    }

    @RequestMapping("/downloadMusic/{bucket}/{fileName}")
    public Object downloadMusic(@PathVariable("bucket") String bucket, @PathVariable("fileName") String fileName) {
        try {
            return service.downloadMusic(URLDecoder.decode(bucket, "UTF-8"), URLDecoder.decode(fileName, "UTF-8"));
        } catch (Exception e) {
            log.error("downloadMusic:error\n", e);
        }
        return 404;
    }

    @RequestMapping("/snoopingBucket/{bucket}/{page}/{rows}")
    public Object snoopingBucket(@PathVariable("bucket") String bucket, @PathVariable("page") int page, @PathVariable("rows") int rows) {
        try {
            return service.snoopingBucket(URLDecoder.decode(bucket, "UTF-8"), page, rows);
        } catch (Exception e) {
            log.error("listPic:error\n", e);
        }
        return 404;
    }

    @RequestMapping("/randomImage")
    public Object randomImage() {
        return service.random();
    }

}
