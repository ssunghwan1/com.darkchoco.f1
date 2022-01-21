package com.darkchoco.f1.web.dto;

import com.darkchoco.f1.domain.result.RaceResult;
import lombok.Getter;


@Getter
public class ResultResponseDto {
    private String title;
    private String raceDate;
    private String circuit;
    private String ranking;
    private String driver;
    private String notes;
    private int point;

    public ResultResponseDto(RaceResult entity){
        this.title = entity.getTitle();
        this.circuit = entity.getCircuit();
        this.raceDate = entity.getRaceDate();
        this.ranking = entity.getRanking();
        this.driver = entity.getDriver();
        this.notes = entity.getNotes();
        this.point =entity.getPoint();

    }
}
