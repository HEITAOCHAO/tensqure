package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.IdWorker;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private IdWorker idWorker;

	/**
	 * 添加文章索引
	 * @param article
	 * @return
	 */
	public Article add(Article article){
		article.setId(idWorker.nextId()+"");
		return articleDao.save(article);
	}

	/**
	 * 根据标题或者内容模糊查询
	 * @param keyword
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Article> search(String keyword,int page,int size){
		Pageable pageable = PageRequest.of(page - 1, size);
		return articleDao.findByTitleOrContentLike(keyword, keyword, pageable);
	}
}
