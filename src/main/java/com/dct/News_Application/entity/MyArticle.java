package com.dct.News_Application.entity;

import lombok.Data;

@Data
public class MyArticle {

    private String author;
    private String title;
    private String description;
    private String publishedAt;
    private String content;
    private String url;
    private String urlToImage;

}

