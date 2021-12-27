package com.laplace;


import com.sun.jndi.toolkit.url.Uri;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * @Author: YEP
 * @CreateDate: 2021/12/14 17:33
 * @Info:
 * @Email:
 */
public class Main {
    public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {
//        System.out.println(URLEncoder.encode("96猫 (クロネコ) - トルコ行進曲 - オワタ (^o^)  (土耳其进行曲 - 完蛋啦＼(^o^)／) [mqms2].mp3", "utf-8"));
//        System.out.println(URLDecoder.decode("96猫+(クロネコ)+-+トルコ行進曲+-+オワタ+(^o^)++(土耳其进行曲+-+完蛋啦＼(^o^)／)+[mqms2].mp3", "utf-8"));


        int[] houses = {1,5};
        int[] heaters = {2};
        Solution solution = new Solution();
        System.out.println(solution.findRadius(houses, heaters));
    }


    static class Solution {
        public int findRadius(int[] houses, int[] heaters) {
            HashMap<Integer, Integer> map = new HashMap<>();
            boolean isFirst = true;
            for (int heater : heaters) {
                if (isFirst) {
                    for (int house : houses) {
                        map.put(house, Math.abs(house - heater));
                    }
                    isFirst = false;
                } else {
                    for (int house : houses) {
                        map.put(house, Math.min(map.get(house), Math.abs(house - heater)));
                    }
                }
            }
            final int[] end = {0};
            map.forEach(new BiConsumer<Integer, Integer>() {
                @Override
                public void accept(Integer integer, Integer integer2) {
                    end[0] = Math.max(integer2, end[0]);
                }
            });


            return end[0];
        }
    }
}
