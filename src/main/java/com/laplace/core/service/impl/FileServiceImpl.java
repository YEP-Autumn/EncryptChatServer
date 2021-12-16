package com.laplace.core.service.impl;

import com.laplace.core.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/16 10:27
 * @Info:
 * @Email:
 */

@Component
@Slf4j
public class FileServiceImpl implements FileService {

    @Override
    public Object uploadPic(MultipartFile multipartFile) {
        log.info("这是Slf4j的日志");
        log.info(multipartFile.getContentType());
        return null;
    }


    @Override
    public Object uploadMusic(MultipartFile multipartFile) {
        log.info("这是Slf4j的日志");
        log.info(multipartFile.getContentType());
        return null;
    }

}
