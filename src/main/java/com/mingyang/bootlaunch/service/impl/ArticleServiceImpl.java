package com.mingyang.bootlaunch.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mingyang.bootlaunch.mapper.ArticleMapper;
import com.mingyang.bootlaunch.entity.Article;
import com.mingyang.bootlaunch.service.ArticleService;
/**
 * @author:  ymy
 * @program: boot-lanuch
 * @description: ${description}
 * @date: 2022/6/5 1:04
 * @version: 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{

}
