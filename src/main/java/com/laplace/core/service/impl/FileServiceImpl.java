package com.laplace.core.service.impl;

import com.laplace.core.service.FileService;
import com.laplace.core.utils.MinioUtils;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/16 10:27
 * @Info:
 * @Email:
 */

@Component
@Slf4j
public class FileServiceImpl implements FileService {

    @Resource
    MinioClient minioClient;

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

    @Override
    public Object downloadPic(String bucket, String fileName) {
        byte[] objToByte = MinioUtils.getObjToByte(minioClient, bucket, fileName);
        if (objToByte == null) return 404;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("image/png;image/jpg"));
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<byte[]>(objToByte, headers, HttpStatus.OK);
    }

    @Override
    public Object downloadMusic(String bucket, String fileName) {
        byte[] objToByte = MinioUtils.getObjToByte(minioClient, bucket, fileName);
        if (objToByte == null) return 404;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<byte[]>(objToByte, headers, HttpStatus.OK);
    }
}
