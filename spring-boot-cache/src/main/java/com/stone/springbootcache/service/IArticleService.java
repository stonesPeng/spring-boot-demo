package com.stone.springbootcache.service;

import com.stone.springbootcache.entity.Article;

import java.util.List;

public interface IArticleService {
    List<Article> getAllArticles();

    Article getArticleById(long articleId);

    Article addArticle(Article article);

    Article updateArticle(Article article);

    void deleteArticle(long articleId);
}
