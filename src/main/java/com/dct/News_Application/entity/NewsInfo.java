package com.dct.News_Application.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsInfo {

    private Long id;
    private String title;
    private String description;
    private String url;
    private String content;
    private String imageUrl;
}
