package com.laplace.core.controller;

import com.laplace.core.service.FileService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/16 9:57
 * @Info:
 * @Email:
 */
@RestController
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
}
