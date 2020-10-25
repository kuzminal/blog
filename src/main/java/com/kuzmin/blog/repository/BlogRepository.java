package com.kuzmin.blog.repository;

import com.kuzmin.blog.model.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BlogRepository extends ElasticsearchRepository<Blog, String>, BlogCustomRepository {
    public List<Blog> findByTitleAndBody(String title, String body);
}
