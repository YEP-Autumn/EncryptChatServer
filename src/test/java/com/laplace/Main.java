package com.laplace;

import io.minio.*;
import io.minio.errors.*;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/14 17:33
 * @Info:
 * @Email:
 */
public class Main {
    public static void main(String[] args) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioClient minioClient = MinioClient.builder().endpoint("http://81.68.81.151:9090").credentials("YEP", "2017248646").build();
        File file = new File("C:\\Users\\admin\\Desktop\\music");
        File[] files = file.listFiles();
        assert files != null;
        for (File f : files) {
            String path = f.getAbsolutePath();
            if (path.endsWith(".mp3") || path.endsWith(".flac")) {
                minioClient.uploadObject(UploadObjectArgs.builder()
                        .bucket("music")
                        .object(f.getName())
                        .filename(path)
                        .build());
            }
        }
    }
}
