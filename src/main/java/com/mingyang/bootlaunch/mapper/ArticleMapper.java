package com.mingyang.bootlaunch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mingyang.bootlaunch.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author:  ymy
 * @program: boot-lanuch
 * @description: ${description}
 * @date: 2022/6/5 1:07
 * @version: 1.0
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}