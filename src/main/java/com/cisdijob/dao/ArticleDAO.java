package com.cisdijob.dao;

import java.util.List;

import com.cisdijob.model.entity.Article;

public interface ArticleDAO {
	public Article getArticleById(int id);
	public List<Article>  getArticleList();
	public void insertArticle(Article article);
}
