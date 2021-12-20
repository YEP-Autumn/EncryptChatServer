package com.laplace;

import com.laplace.core.utils.MinioUtils;
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
import java.net.URLDecoder;
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
        System.out.println(MinioUtils.verifyFile(minioClient, "music", "96猫 (クロネコ) - トルコ行進曲 - オワタ (^o^)  (土耳其进行曲 - 完蛋啦＼(^o^)／) [mqms2].mp3"));
    }

}
