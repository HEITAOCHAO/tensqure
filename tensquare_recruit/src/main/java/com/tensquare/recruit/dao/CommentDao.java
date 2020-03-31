package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentDao extends MongoRepository<Comment,String> {

	/**
	 * 根据文章id，获取评论列表
	 * @param articleid
	 * @return
	 */
	List<Comment> findByArticleid(String articleid);


}
