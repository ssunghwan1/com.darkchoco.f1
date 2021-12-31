package com.darkchoco.f1.web.dto;

import com.darkchoco.f1.domain.posts.Posts;
import com.darkchoco.f1.domain.result.Result;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
public class ResultListResponseDto {
    private Long id;
    private String title;
    private String raceDate;
    private String circuit;
    private String ranking;
    private String driver;
    private String notes;

    public ResultListResponseDto(Result entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.raceDate = entity.getRaceDate();
        this.circuit = entity.getCircuit();
        this.ranking = entity.getRanking();
        this.driver = entity.getDriver();
        this.notes = entity.getNotes();
    }

}
