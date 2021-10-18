package com.laplace.controller;

import com.laplace.mapper.IsOnline;
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
public class MyController {

    @Resource
    IsOnline isOnline;

    @ResponseBody
    @RequestMapping("/isUsed")
    public boolean isUsed(@RequestParam("userId") int userId) {
        if (userId == 0) {
            return true;
        }
        return isOnline.isOnline(userId);
    }

}
