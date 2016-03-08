package com.cisdijob.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisdijob.dao.ArticleDAO;
import com.cisdijob.model.entity.Article;
import com.cisdijob.service.pages.ArticleService;
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDAO articleDAO;
	public Article getArticleById(String id) {
		// TODO Auto-generated method stub
		return articleDAO.getArticleById(id);
	}
	public List<Article> getArticleList() {
		// TODO Auto-generated method stub
		return articleDAO.getArticleList();
	}
	public void insertArticle(Article article) {
		// TODO Auto-generated method stub
		articleDAO.insertArticle(article);
	}
	public int articleCount(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return articleDAO.articleCount(map);
	}
	public List<Article> getArticleListByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return articleDAO.getArticleListByMap(map);
	}
	public int articleAllCounts() {
		// TODO Auto-generated method stub
		return articleDAO.articleAllCounts();
	}
	public void deleteArticle(String articleId) {
		// TODO Auto-generated method stub
		articleDAO.deleteArticle(articleId);
	}

}
