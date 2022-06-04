package com.mingyang.bootlaunch.controller;

import com.mingyang.bootlaunch.common.Result;
import com.mingyang.bootlaunch.common.ResultCode;
import com.mingyang.bootlaunch.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/6/3 18:13
 * @version: 1.0
 */
@Slf4j
@RestController
public class ArticleController {

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    @GetMapping("/article/{id}")
    private Result getArticle(@PathVariable("id") Long id) {
        Article article = new Article();
        article.setId(id);
        article.setTitle("title");
        article.setContent("content");
        log.info("article:{}", article);
        return Result.success(article);
    }

    /**
     * 新增一片文章
     */
    @PostMapping("/article")
    private Result addArticle(@RequestBody Article article){
        log.info("article:{}", article);
        return Result.success();
    }
    /**
     * 更新一片文章
     */
    @PutMapping("/article")
    private Result updateArticle(@RequestBody Article article){
        if(article.getId() == null){
            return Result.failure(ResultCode.PARAM_ERROR);
        }
        log.info("article:{}", article);
        return Result.success();
    }

    /**
     * 删除一片文章
     * @param id
     * @return
     */
    @DeleteMapping("/article/{id}")
    private Result deleteArticle(@PathVariable("id") Long id){
        log.info("id:{}", id);
        return Result.success();
    }
}
