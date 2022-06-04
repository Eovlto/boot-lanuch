package com.mingyang.bootlaunch;

import com.mingyang.bootlaunch.common.config.Family;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BootLanuchApplicationTests {

    @Resource
    private Family family;

    @Test
    void contextLoads() {
        System.out.println(family);
    }

}
