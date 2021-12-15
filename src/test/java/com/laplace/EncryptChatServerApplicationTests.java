package com.laplace;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.function.Consumer;


@SpringBootTest
class EncryptChatServerApplicationTests {

    @Resource
    MinioClient minioClient;


    @Test
    public void server() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<Bucket> buckets = minioClient.listBuckets();
        buckets.forEach(new Consumer<Bucket>() {
            @Override
            public void accept(Bucket bucket) {
                System.out.println(bucket.name());
                System.out.println(bucket.creationDate());
            }
        });

        System.out.println(minioClient.bucketExists(BucketExistsArgs.builder().bucket("my-bucketname").build()));
        System.out.println(minioClient.bucketExists(BucketExistsArgs.builder().bucket("picture").build()));
//        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
//                .bucket("picture")
//                .prefix("3")
//                .build());
//
//        for (Result<Item> result : results) {
//            System.out.println(result.get().objectName());
//        }
        GetObjectResponse picture = minioClient.getObject(GetObjectArgs.builder()
                .bucket("picture")
                .object("01015c9f0048f4a61a08dab45daf8783c5a29e80.png")
                .build());
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\admin\\Desktop\\" + picture.object());
        IOUtils.copy(picture,outputStream);
    }

}
