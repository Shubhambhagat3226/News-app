package com.dct.News_Application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {

    private String status;
    private int totalResults;
    private List<MyArticle> articles;

}