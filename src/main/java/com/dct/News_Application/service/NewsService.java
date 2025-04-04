package com.dct.News_Application.service;

import com.dct.News_Application.entity.MyArticle;
import com.dct.News_Application.entity.News;
import com.dct.News_Application.entity.NewsInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    static long newsId;
    private List<NewsInfo> newsList = new ArrayList<>();

    @Value("${news.api.key}")
    private String apiKey;

    @Value("${news.api.url}")
    private String apiURL;

    private final RestTemplate restTemplate = new RestTemplate();

    static {
        newsId = 0;
    }

    public List<NewsInfo> searchNews(String search) {

        long start = newsId;

        String finalUrl = apiURL.replace("SEARCH", search).replace("KEY", apiKey);

        News response = restTemplate.getForObject(finalUrl, News.class);

        for (MyArticle article : response.getArticles()) {
            newsList.add(
                    NewsInfo.builder()
                            .id(newsId++)
                            .title(article.getTitle())
                            .description(article.getDescription())
                            .imageUrl(article.getUrlToImage())
                            .url(article.getUrl())
                            .content(article.getContent())
                            .build()
            );
        }

        return newsList.subList( (int) start, (int) newsId);
    }


    public String getNewsByUrl(String url) {

        try {
            Document document = Jsoup.connect(url).get();

            Elements article = document.select("article");
            System.out.println(article);

            StringBuffer content = new StringBuffer();

            for (Element news : article) {
                for (Element paragraph : news.select("p")) {
                    content.append(paragraph.wholeText().trim())
                            .append("\n\n");
                }
            }

            return content.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public NewsInfo getNewsById(long id) {

        NewsInfo newsInfo = null;
        for (NewsInfo news : newsList) {
            if (news.getId() == id) {
                newsInfo = news;
                break;
            }
        }
        if (newsInfo == null) return null;
        try {
            Document document = Jsoup.connect(newsInfo.getUrl()).get();

            Elements article = document.select("article");
            System.out.println(article);

            StringBuilder content = new StringBuilder();
            for (Element paragraph : article.select("p")) {
                content.append(paragraph);

            }

            String cleanContent = Jsoup.clean(content.toString(), Safelist.basic());


            if (!content.isEmpty()) {
                newsInfo.setContent(cleanContent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsInfo;
    }
}
