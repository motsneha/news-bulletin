package com.he;

import com.he.data.News;
import com.he.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@CrossOrigin(origins = "*", allowCredentials = "true")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    NewsRepository newsRepository;

    @GetMapping("/news")
    public List<News> getNews() {
        List<News> news = new ArrayList<>();
        newsRepository.findAll().forEach(e -> news.add(e));
        return news;
    }

    @GetMapping("/news/{article}")
    public List<News> getNewsByArticle(@Param("article") String article) {
        return newsRepository.findByNewsHeadline(article);
    }

}