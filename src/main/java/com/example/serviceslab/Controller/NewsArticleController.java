package com.example.serviceslab.Controller;

import com.example.serviceslab.ApiResponse.ApiResponse;
import com.example.serviceslab.Model.NewsArticle;
import com.example.serviceslab.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/news-article")
@RequiredArgsConstructor
public class NewsArticleController {


    private final NewsArticleService newsArticleService;

    @GetMapping("/articles")
    public ResponseEntity getAllArticles(){
        return ResponseEntity.status(200).body(newsArticleService.getAllArticles());
    }

    @PostMapping("/add")
    public ResponseEntity addArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        newsArticleService.addArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("article added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateArticle(@PathVariable String id, @RequestBody @Valid NewsArticle newsArticle, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdated = newsArticleService.updateArticle(id,newsArticle);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("article updated"));
        }else return ResponseEntity.status(400).body(new ApiResponse("article not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteArticle(@PathVariable String id){
        boolean isRemoved = newsArticleService.removeArticle(id);
        if(isRemoved){
            return ResponseEntity.status(200).body(new ApiResponse("article removed"));
        }else return ResponseEntity.status(400).body(new ApiResponse("article doesn't exists"));
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity publishArticle(@PathVariable String id){
        boolean isPublished = newsArticleService.publishArticle(id);
        if(isPublished){
            return ResponseEntity.status(200).body(new ApiResponse("article published"));
        }else return ResponseEntity.status(400).body(new ApiResponse("article not found"));
    }

    @GetMapping("/published-articles")
    public ResponseEntity getPublishedArticles(){
        ArrayList<NewsArticle> publishedArticles = newsArticleService.getPublishedArticle();

        if(publishedArticles.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("no published articles"));
        }
        return ResponseEntity.status(200).body(publishedArticles);
    }

    @GetMapping("/articles/{category}")
    public ResponseEntity getArticlesByCategory(@PathVariable String category){
        ArrayList<NewsArticle> articlesByCategory = newsArticleService.getArticlesByCategory(category);

        if(articlesByCategory.isEmpty()){
            ResponseEntity.status(400).body(new ApiResponse("there is no article by "+category+ " category"));
        }
        return ResponseEntity.status(200).body(articlesByCategory);
    }


}
