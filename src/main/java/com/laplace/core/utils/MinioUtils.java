package com.laplace.core.utils;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/20 10:48
 * @Info:
 * @Email:
 */
public class MinioUtils {

    /**
     * 验证minio文件服务器中文件是否存在
     *
     * @param client
     * @param bucket
     * @param fileName
     * @return
     */
    public static boolean verifyFile(MinioClient client, String bucket, String fileName) {
        boolean exists = verifyBucket(client,bucket);
        if (exists) {
            Iterable<Result<Item>> results = client.listObjects(ListObjectsArgs.builder()
                    .bucket(bucket)
                    .prefix(fileName)
                    .maxKeys(1)
                    .build());
            if (results.iterator().hasNext()) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取某个文件的byte[]
     *
     * @param client
     * @param bucket
     * @param fileName
     * @return
     */
    public static byte[] getObjToByte(MinioClient client, String bucket, String fileName) {
        if (!verifyFile(client, bucket, fileName)) return null;
        try {
            GetObjectResponse object = client.getObject(GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(fileName)
                    .build());
            return FileCopyUtils.copyToByteArray(object);

        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证minio服务器中bucket是否存在
     *
     * @param client
     * @param bucket
     * @return
     */
    public static boolean verifyBucket(MinioClient client, String bucket) {
        try {
            boolean exists = client.bucketExists(BucketExistsArgs.builder()
                    .bucket(bucket)
                    .build());
            if (exists) return true;
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
