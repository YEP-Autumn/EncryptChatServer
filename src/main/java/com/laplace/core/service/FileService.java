package com.laplace.core.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/16 10:26
 * @Info:
 * @Email:
 */

@Service
public interface FileService {

    Object uploadPic(MultipartFile multipartFile);


    Object uploadMusic(MultipartFile multipartFile);
}
