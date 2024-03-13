package com.example.serviceslab.Service;


import com.example.serviceslab.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsArticleService {
    ArrayList<NewsArticle> newsArticles = new ArrayList<>();


    public ArrayList<NewsArticle> getAllArticles(){
        return newsArticles;
    }

    public void addArticle(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }

    public boolean updateArticle(String id,NewsArticle newsArticle){
        for(int i = 0; i< newsArticles.size();i++){
            if(newsArticles.get(i).getId().equalsIgnoreCase(id)){
                newsArticles.set(i,newsArticle);
                return true;
            }
        }
        return false;
    }

    public boolean removeArticle(String id){
        for(int i =0; i< newsArticles.size();i++){
            if(newsArticles.get(i).getId().equalsIgnoreCase(id)){
                newsArticles.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean publishArticle(String id){
        for(int i = 0; i<newsArticles.size();i++){
            if(newsArticles.get(i).getId().equalsIgnoreCase(id)){
                newsArticles.get(i).setPublishDate(LocalDate.now());
                newsArticles.get(i).setIsPublished(true);
                return true;
            }
        }
        return false;
    }

    public ArrayList<NewsArticle> getPublishedArticle(){
        ArrayList<NewsArticle> publishedArticles = new ArrayList<>();
        for(NewsArticle newsArticle:newsArticles){
            if(newsArticle.getIsPublished()){
                publishedArticles.add(newsArticle);
            }
        }
        return publishedArticles;
    }

    public ArrayList<NewsArticle> getArticlesByCategory(String category){
        ArrayList<NewsArticle> articlesByCategory = new ArrayList<>();
        for(NewsArticle newsArticle:newsArticles){
            if(newsArticle.getCategory().equalsIgnoreCase(category)){
                articlesByCategory.add(newsArticle);
            }
        }
        return articlesByCategory;
    }


}
