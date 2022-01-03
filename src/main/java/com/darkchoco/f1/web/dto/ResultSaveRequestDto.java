package com.darkchoco.f1.web.dto;

import com.darkchoco.f1.domain.posts.Posts;
import com.darkchoco.f1.domain.result.Result;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResultSaveRequestDto {
    private String title;
    private String raceDate;
    private String circuit;
    private String ranking;
    private String driver;
    private String notes;
    private String point;

    @Builder
    public ResultSaveRequestDto(String title, String circuit, String raceDate, String ranking, String driver, String notes, String point){
        this.title = title;
        this.circuit = circuit;
        this.raceDate = raceDate;
        this.ranking = ranking;
        this.driver = driver;
        this.notes = notes;
        this.point =point;
    }

    public Result toEntity(){
        return Result.builder()
                .title(title)
                .circuit(circuit)
                .raceDate(raceDate)
                .ranking(ranking)
                .driver(driver)
                .notes(notes)
                .point(point)
                .build();
    }

}
