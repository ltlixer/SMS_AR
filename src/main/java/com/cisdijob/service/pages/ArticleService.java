package com.cisdijob.service.pages;

import java.util.List;

import com.cisdijob.model.entity.Article;

public interface ArticleService {
	public Article getArticleById(int id);
	public List<Article>  getArticleList();
	public void insertArticle(Article article);
}
