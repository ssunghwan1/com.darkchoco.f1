package com.darkchoco.f1.web.dto;

import com.darkchoco.f1.domain.result.RaceResult;
import lombok.Getter;

@Getter
public class ResultListResponseDto {
    private Long id;
    private String title;
    private String raceDate;
    private String circuit;
    private String ranking;
    private String driver;
    private String notes;
    private int point;

    public ResultListResponseDto(RaceResult entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.raceDate = entity.getRaceDate();
        this.circuit = entity.getCircuit();
        this.ranking = entity.getRanking();
        this.driver = entity.getDriver();
        this.notes = entity.getNotes();
        this.point = entity.getPoint();
    }

}
