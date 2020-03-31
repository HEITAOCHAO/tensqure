package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Comment;
import com.tensquare.recruit.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result addComment(@RequestBody Comment comment){
		return new Result(true, StatusCode.OK,"评论成功",commentService.add(comment));
	}

	/**
	 * 删除文章评论
	 * @param commenid
	 * @return
	 */
	@RequestMapping(value = "/{commentid}",method = RequestMethod.DELETE)
	public Result deleteByCommentId(@PathVariable String commenid){
		commentService.delete(commenid);
		return new Result(true,StatusCode.OK,"删除成功");
	}

	/**
	 * 根据文章id查询评论列表
	 * @param articleid
	 * @return
	 */
	@RequestMapping(value = {"article/{articleid}"},method = RequestMethod.GET)
	public Result findByArticle(@PathVariable String articleid){
		return new Result(true, StatusCode.OK,"查询成功",commentService.findByArticleid(articleid));
	}
}
