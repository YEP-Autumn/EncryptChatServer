package com.laplace.core.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/14 17:17
 * @Info:
 * @Email:
 */

@ConfigurationProperties(prefix = "minio")
@Component
@Data
public class MinioConfig {
        private String endpoint;
        private String accessKey;
        private String secretKey;
}
