package com.mingyang.bootlaunch.common.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/6/4 21:20
 * @version: 1.0
 */
public class MixPropertySourceFactory extends DefaultPropertySourceFactory
{
    @Override
    public PropertySource<?> createPropertySource(@Nullable String name, EncodedResource resource) throws IOException {
        String sourceName = name != null ? name : resource.getResource().getFilename();
        if(sourceName != null && sourceName.endsWith(".yml") || sourceName.endsWith(".yaml")) {
            Properties properties = loadYaml(resource);
            return new PropertiesPropertySource(sourceName, properties);
        }else {
            return super.createPropertySource(name, resource);
        }
    }

    private Properties loadYaml(EncodedResource resource) throws IOException {
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(resource.getResource());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
}


