package com.darkchoco.f1.web.dto;

import com.darkchoco.f1.domain.posts.Posts;
import com.darkchoco.f1.domain.result.Result;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultSaveRequestDto {
    private String title;
    private String raceDate;
    private String circuit;
    private String ranking;
    private String driver;
    private String notes;

    @Builder
    public ResultSaveRequestDto(String title, String circuit, String raceDate, String ranking, String driver, String notes){
        this.title = title;
        this.circuit = circuit;
        this.raceDate = raceDate;
        this.ranking = ranking;
        this.driver = driver;
        this.notes = notes;
    }

    public Result toEntity(){
        return Result.builder()
                .title(title)
                .circuit(circuit)
                .raceDate(raceDate)
                .ranking(ranking)
                .driver(driver)
                .notes(notes)
                .build();
    }
}
