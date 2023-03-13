package com.mingyang.bootlaunch.common.utils;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description: MD5工具类
 * @date: 2022/9/18 1:34
 * @version: 1.0
 */
public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md2Hex(src);
    }

    private static final String salt = "n2FdyTvhaOfGMMYv";

    public static String inputPassToFromPass(String inputPass) {
        String str = salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(9) + salt.charAt(7);
        return md5(str);
    }
    public static String fromPassToDBPass(String fromPass,String salt) {
        String str =  salt.charAt(0)+salt.charAt(9)+fromPass+salt.charAt(3)+salt.charAt(1) + salt.charAt(7);
        return md5(str);
    }
    public static String inputPassToDBPass(String inputPass,String salt) {
        String fromPass = inputPassToFromPass(inputPass);
        return fromPassToDBPass(fromPass, salt);
    }

}
