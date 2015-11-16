package com.cisdijob.dao;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.Article;

public interface ArticleDAO {
	public Article getArticleById(String id);
	public List<Article>  getArticleList();
	public void insertArticle(Article article);
	public int articleCount(Map<String,Object> map);
	public List<Article> getArticleListByMap(Map<String,Object> map);
	public int articleAllCounts();
}
