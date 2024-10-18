package com.example.demo.Service;

import com.example.demo.Model.Category;
import com.example.demo.Model.Comment;
import com.example.demo.Model.News;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.NewsRepository;
import com.example.demo.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CommentRepository commentRepository;

    public List<News> getNewsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return new ArrayList<>(category.getNews());
    }

    public News getNewsById(Long newsId) {
        return newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News not found"));
    }

    public Comment addCommentToNews(Long newsId, Comment comment) {
        News news = getNewsById(newsId);
        comment.setNews(news);
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

}
