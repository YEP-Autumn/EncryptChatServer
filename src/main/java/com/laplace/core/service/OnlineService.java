package com.laplace.core.service;

import org.springframework.stereotype.Service;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/16 10:20
 * @Info:
 * @Email:
 */
@Service
public interface OnlineService {
    boolean isOnline(long userId);
}
