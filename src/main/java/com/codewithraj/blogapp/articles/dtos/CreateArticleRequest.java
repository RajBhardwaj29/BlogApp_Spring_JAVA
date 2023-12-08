package com.codewithraj.blogapp.articles.dtos;

import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Setter(AccessLevel.NONE)
@Data
public class CreateArticleRequest {
    @NonNull
    private String title;

    @NonNull
    private String body;

    @Nullable
    private String subtitle;

}
