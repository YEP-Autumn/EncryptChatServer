package com.laplace;


import com.sun.jndi.toolkit.url.Uri;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/14 17:33
 * @Info:
 * @Email:
 */
public class Main {
    public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("96猫 (クロネコ) - トルコ行進曲 - オワタ (^o^)  (土耳其进行曲 - 完蛋啦＼(^o^)／) [mqms2].mp3", "utf-8"));
        System.out.println(URLDecoder.decode("96猫+(クロネコ)+-+トルコ行進曲+-+オワタ+(^o^)++(土耳其进行曲+-+完蛋啦＼(^o^)／)+[mqms2].mp3","utf-8"));
    }
}
