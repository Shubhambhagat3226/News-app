package com.dct.News_Application.controller;

import com.dct.News_Application.entity.News;
import com.dct.News_Application.entity.NewsInfo;
import com.dct.News_Application.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsController {

    private final NewsService newsService;

    private static List<String> searchHistory = new ArrayList<>();

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/home")
    public String homePage() {

        return "home";
    }

    @GetMapping("/search")
    public String searchNews(@RequestParam("query") String query, Model model) {
        if (!searchHistory.contains(query)) {  // Avoid duplicate entries
            searchHistory.add(0, query);  // Store latest searches at the top
        }

        model.addAttribute("searchHistory", searchHistory);
        model.addAttribute("query", query);  // Send current search term

        List<NewsInfo> newsList = newsService.searchNews(query);
        model.addAttribute("newsList", newsList);
        return "home";
    }

    @GetMapping("/news/{id}")
    public String getNewsDetail(
            @PathVariable("id") long id,
            Model model) {

        NewsInfo news = newsService.getNewsById(id);

        if (news == null) {
            return "error";
        }

        model.addAttribute("news", news);
        return "news-detail";
    }
}
