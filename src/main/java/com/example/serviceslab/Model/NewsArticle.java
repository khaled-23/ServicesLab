package com.example.serviceslab.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {
    @NotEmpty(message = "id should not be empty")
    private String id;
    @NotEmpty(message = "title can not be empty")
    @Size(max = 100, message = "title should not exceed 100 characters")
    private String title;
    @NotEmpty(message = "author can not be empty")
    @Size(min = 5, max = 20, message = "author should be at range 5-20 characters")
    private String author;
    @NotEmpty(message = "content should not be empty")
    @Size(min=201, message = "content should be more than 200 characters")
    private String content;
    @NotEmpty(message = "category should not be empty")
    @Pattern(regexp = "^(politics|sports|technology)$")
    private String category;
    @NotEmpty(message = "image url should not be empty")
    private String imageUrl;
    @AssertFalse(message = "isPublished should be false")
    private Boolean isPublished;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
}
