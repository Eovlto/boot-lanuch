package com.mingyang.bootlaunch.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mingyang.bootlaunch.common.result.Result;
import com.mingyang.bootlaunch.entity.Article;
import com.mingyang.bootlaunch.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* (article)表控制层
*
* @author xxxxx
*/
@Api(tags = "文章接口")
@RestController
@RequestMapping("/article")
public class ArticleController {
    /**
    * 服务对象
    */
    @Resource
    private ArticleService articleService;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @ApiOperation("通过主键查询单条数据")
    @ApiImplicitParam(name = "id", value = "文章表主键id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Result selectOne(@PathVariable("id")Integer id) {
        return Result.success(articleService.getById(id));
    }

    /**
     * 查询所有数据
     * @return 所有数据
     */
    @ApiOperation("查询所有数据")
    @GetMapping("/all")
    public Result selectAll() {
        return Result.success(articleService.list());
    }

    /**
     * 新增数据
     * @param article 实体对象
     * @return  Result 新增结果
     */
    @ApiOperation("新增数据")
    @ApiImplicitParam(name = "article", value = "文章表实体对象", required = true, dataType = "Article")
    @PostMapping("/add")
    public Result insert(@RequestBody Article article) {
        return Result.success(articleService.save(article));
    }

    /**
     * 修改数据
     * @param article 实体对象
     * @return  Result 修改结果
     */
    @ApiOperation("修改数据")
    @ApiImplicitParam(name = "article", value = "文章表实体对象", required = true, dataType = "Article")
    @PutMapping("/update")
    public Result update(@RequestBody Article article) {
        return Result.success(articleService.updateById(article));
    }

    /**
     * 删除数据
     * @param id 主键
     * @return  Result 删除结果
     */
    @ApiOperation("删除数据")
    @ApiImplicitParam(name = "id", value = "文章表主键id", required = true, dataType = "Long")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id")Integer id) {
        return Result.success(articleService.removeById(id));
    }

    /**
     * 分页查询数据
     * @param page 当前页
     * @param size 每页显示条数
     * @return  Result 分页结果
     */
    @ApiOperation("分页查询数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "Integer"),
        @ApiImplicitParam(name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping("/page/{page}/{size}")
    public Result page(@PathVariable("page")Integer page, @PathVariable("size")Integer size) {
        return Result.success(articleService.page(new Page<Article>(page, size), null));
    }
}
