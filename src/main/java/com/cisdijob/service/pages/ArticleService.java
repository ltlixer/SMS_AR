package com.cisdijob.service.pages;

import java.util.List;
import java.util.Map;

import com.cisdijob.model.entity.Article;

public interface ArticleService {
	public Article getArticleById(String id);
	public List<Article>  getArticleList();
	public void insertArticle(Article article);
	public int articleCount(Map<String,Object> map);
	public List<Article> getArticleListByMap(Map<String,Object> map);
	public int articleAllCounts();
	/**
	 * 根据课文id删除课文
	 * @param articleId 课文id
	 */
	public void deleteArticle(String articleId);
}
