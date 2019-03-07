package com.stone.springbootcache.repository;

import com.stone.springbootcache.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
