package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	/**
	 * 添加索引
	 * @param article
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result add(@RequestBody Article article){
		return new Result(true, StatusCode.OK,"保存成功",articleService.add(article));
	}

	/**
	 * 根据关键字模糊查询
	 * @param keyword
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/search/{keyword}/{page}/{size}",method = RequestMethod.GET)
	public Result search(@PathVariable String keyword,@PathVariable int page,@PathVariable int size){
		Page<Article> pageData = articleService.search(keyword, page, size);
		return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(pageData.getTotalElements(),pageData.getContent()));
	}
}
