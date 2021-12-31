package com.darkchoco.f1.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public ResultUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
