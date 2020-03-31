package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.CommentDao;
import com.tensquare.recruit.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public Comment add(Comment comment){
		comment.set_id(idWorker.nextId()+"");
		return commentDao.save(comment);
	}

	/**
	 * 根据文章id获取评论列表
	 * @param articleid
	 * @return
	 */
	public List<Comment> findByArticleid(String articleid){
		return commentDao.findByArticleid(articleid);
	}

	/**
	 * 删除评论
	 * @param commentId
	 */
	public void delete(String commentId){
		commentDao.deleteById(commentId);
	}
}
