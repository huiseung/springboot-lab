package com.example.page.web.dto;

import com.example.page.domain.post.Post;
import lombok.Getter;


@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private String author;

    public PostListResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
    }
}
