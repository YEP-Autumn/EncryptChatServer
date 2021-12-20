package com.laplace.core.controller;

import com.laplace.core.service.FileService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
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
public class FileController {

    @Resource
    FileService service;

    @PostMapping("/uploadPic")
    public Object uploadPic(@RequestParam("image") MultipartFile multipartFile) {
        return service.uploadPic(multipartFile);
    }


    @PostMapping("/uploadMusic")
    public Object uploadMusic(@RequestParam("music") MultipartFile multipartFile) {
        return service.uploadMusic(multipartFile);
    }

    @RequestMapping("/downloadPic/{bucket}/{fileName}")
    public Object downloadPic(@PathVariable("bucket") String bucket, @PathVariable("fileName") String fileName) throws UnsupportedEncodingException {
        return service.downloadPic(URLDecoder.decode(bucket,"UTF-8"), URLDecoder.decode(fileName,"UTF-8"));
    }

    @RequestMapping("/downloadMusic/{bucket}/{fileName}")
    public Object downloadMusic(@PathVariable("bucket") String bucket, @PathVariable("fileName") String fileName) throws UnsupportedEncodingException {  String n = "96猫 (クロネコ) - トルコ行進曲 - オワタ (^o^) (土耳其进行曲 - 完蛋啦＼(^o^)／) [mqms2].mp3";
        return service.downloadMusic(URLDecoder.decode(bucket,"UTF-8"), URLDecoder.decode(fileName,"UTF-8"));
    }
}
