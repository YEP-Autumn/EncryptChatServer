package com.laplace.core.service.impl;

import com.laplace.core.service.FileService;
import com.laplace.core.utils.MinioUtils;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Consumer;

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

    @Override
    public Object snoopingBucket(String bucket, int page, int rows) {

        if (page < 1 || rows < 1) return 404;
        if (!MinioUtils.verifyBucket(minioClient, bucket)) return 404;

        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(bucket)
                .build());
        List<String> total_s = new ArrayList<>();
        results.forEach(new Consumer<Result<Item>>() {
            @SneakyThrows
            @Override
            public void accept(Result<Item> itemResult) {
                total_s.add(itemResult.get().objectName());
            }
        });

        List<String> rtn_s = total_s;
        int size = rtn_s.size();  // 总条数
        int start = rows * (page - 1);  // 起始位置
        if (start > size) {             // 如果起始位置比总条数大，返回null
            rtn_s = new ArrayList<>();
        } else if (start + rows >= size) {  // 如果 起始位置+显示行数 大于 size 则展示 start位置 到 size大小
            rtn_s = rtn_s.subList(start, size);
        } else {                            // 否则 展示 start + rows 列
            rtn_s = rtn_s.subList(start, start + rows);
        }

        HashMap<String, Object> stringListHashMap = new HashMap<>();
        stringListHashMap.put("YEP-Autumn", rtn_s);
        stringListHashMap.put("total", size);

        return stringListHashMap;
    }

    @Override
    public Object random() {
        return null;
    }

}
