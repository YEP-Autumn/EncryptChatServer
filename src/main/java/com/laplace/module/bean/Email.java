package com.laplace.module.bean;

import lombok.Data;

import javax.mail.Multipart;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/27 16:37
 * @Info:
 * @Email:
 */
@Data
public class Email {

    String form;

    Multipart multipart;

    String receive;

    String subject;
}
