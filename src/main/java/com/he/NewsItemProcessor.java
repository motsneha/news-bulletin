package com.he;

import com.he.data.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class NewsItemProcessor implements ItemProcessor<News, News> {

    private static final Logger log = LoggerFactory.getLogger(NewsItemProcessor.class);

    @Override
    public News process(final News news) throws Exception {
        return news;
    }

}