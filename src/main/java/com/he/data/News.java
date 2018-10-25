package com.he.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class News {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String newsHeadline;
    private String newsType;
    private String url;
    private String source;
    private String link;
    private Long dateOfHappening;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsHeadline() {
        return newsHeadline;
    }

    public void setNewsHeadline(String newsHeadline) {
        this.newsHeadline = newsHeadline;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getDateOfHappening() {
        return dateOfHappening;
    }

    public void setDateOfHappening(Long dateOfHappening) {
        this.dateOfHappening = dateOfHappening;
    }
}
