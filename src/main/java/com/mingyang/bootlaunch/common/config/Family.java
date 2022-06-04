package com.mingyang.bootlaunch.common.config;

import com.mingyang.bootlaunch.common.factory.MixPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/6/4 20:53
 * @version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "family")
@PropertySource(value = "classpath:family.yml",factory = MixPropertySourceFactory.class)
public class Family {
    private String name;
    private String age;
}
