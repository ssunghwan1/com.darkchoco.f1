package com.darkchoco.f1.web.dto;

import com.darkchoco.f1.domain.posts.Posts;
import lombok.Getter;


@Getter
public class ResultResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public ResultResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author =entity.getAuthor();
    }
}
