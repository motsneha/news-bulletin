package com.he.repository;

import com.he.data.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface NewsRepository extends CrudRepository<News, Long> {

    List<News> findByNewsHeadline(@Param("newsHeadline") String newsHeadline);

}
