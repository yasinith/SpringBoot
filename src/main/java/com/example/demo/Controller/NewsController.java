package com.example.demo.Controller;

import com.example.demo.Model.Comment;
import com.example.demo.Model.News;
import com.example.demo.Service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/categories/{categoryId}/news")
    public ResponseEntity<List<News>> getNewsByCategory(@PathVariable Long categoryId) {
        List<News> news = newsService.getNewsByCategory(categoryId);
        return ResponseEntity.ok(news);
    }

    @GetMapping("/news/{newsId}")
    public ResponseEntity<News> getNewsById(@PathVariable Long newsId) {
        News news = newsService.getNewsById(newsId);
        return ResponseEntity.ok(news);
    }

    @PostMapping("/news/{newsId}/comments")
    public ResponseEntity<Comment> addCommentToNews(@PathVariable Long newsId, @RequestBody Comment comment) {
        Comment savedComment = newsService.addCommentToNews(newsId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }
}