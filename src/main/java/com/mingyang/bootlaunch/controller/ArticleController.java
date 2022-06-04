package com.mingyang.bootlaunch.controller;

import com.mingyang.bootlaunch.common.result.Result;
import com.mingyang.bootlaunch.common.result.ResultCode;
import com.mingyang.bootlaunch.entity.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ymy
 * @program: boot-lanuch
 * @description:
 * @date: 2022/6/3 18:13
 * @version: 1.0
 */
@Api(tags = "文章接口")
@Slf4j
@RestController
public class ArticleController {

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询文章")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "Long")
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
    @ApiOperation(value = "新增一片文章")
    @ApiImplicitParam(name = "article", value = "文章实体", required = true, dataType = "Article")
    @PostMapping("/article")
    private Result addArticle(@RequestBody Article article){
        log.info("article:{}", article);
        return Result.success(article.getId());
    }
    /**
     * 更新一片文章
     */
    @PutMapping("/article")
    @ApiOperation(value = "更新一片文章")
    @ApiImplicitParam(name = "article", value = "文章实体", required = true, dataType = "Article")
    private Result updateArticle(@RequestBody Article article){
        if(article.getId() == null){
            return Result.failure(ResultCode.PARAM_ERROR);
        }
        log.info("article:{}", article);
        return Result.success(article.getId());
    }

    /**
     * 删除一片文章
     * @param id
     * @return
     */
    @DeleteMapping("/article/{id}")
    @ApiOperation(value = "删除一片文章")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "Long")
    private Result deleteArticle(@PathVariable("id") Long id){
        log.info("id:{}", id);
        return Result.success();
    }
}
