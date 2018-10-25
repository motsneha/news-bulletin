package com.he.config;

import com.he.NewsItemProcessor;
import com.he.data.News;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import javax.sql.DataSource;


@Configuration
@EnableBatchProcessing
public class BatchProcessConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Bean
    public FlatFileItemReader<News> reader() {
        return new FlatFileItemReaderBuilder<News>()
                .name("personItemReader")
                .resource(new ClassPathResource("news_bulletin666200b.csv"))
                .delimited()
                .names(new String[]{"id", "newsHeadline", "url", "source", "newsType", "link", "dateOfHappening"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<News>() {{
                    setTargetType(News.class);
                }})
                .build();
    }

    @Bean
    public NewsItemProcessor processor() {
        return new NewsItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<News> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<News>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO NEWS (id, news_headline, url,source,news_type,link,date_of_happening )" +
                        " VALUES (:id, :newsHeadline, :url,:source,:newsType,:link,:dateOfHappening)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<News> writer) {
        return stepBuilderFactory.get("step1")
                .<News, News>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}

