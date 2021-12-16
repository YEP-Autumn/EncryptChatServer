package com.laplace.core.controller;

import com.laplace.core.service.OnlineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: YEP
 * @CreateDate: 2021/10/18 13:23
 * @Info:
 * @Email:
 */
@Controller
public class OnlineController {

    @Resource
    OnlineService service;

    @ResponseBody
    @RequestMapping("/isUsed")
    public boolean isUsed(@RequestParam("userId") int userId) {
        if (userId == 0) {
            return true;
        }
        return service.isOnline(userId);
    }

}
