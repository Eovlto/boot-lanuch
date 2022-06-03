package com.mingyang.bootlaunch.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description: lomback 测试
 * @date: 2022/6/3 17:28
 * @version: 1.0
 */
@Data
@Slf4j
@Builder
public class LombokPojo {
    private String name;
    private Integer age;
}
