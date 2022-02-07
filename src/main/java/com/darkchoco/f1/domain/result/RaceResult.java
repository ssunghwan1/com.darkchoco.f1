package com.darkchoco.f1.domain.result;

import com.darkchoco.f1.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class RaceResult extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String raceDate;
    private String circuit;
    private String ranking;
    private String driver;
    private String notes;
    private int point;

    @Builder
    public RaceResult(String title, String circuit, String raceDate, String ranking, String driver, String notes, int point){
        this.title = title;
        this.circuit = circuit;
        this.raceDate = raceDate;
        this.ranking = ranking;
        this.driver = driver;
        this.notes = notes;
        this.point = point;

    }


//    public void update(String title, String circuit, String raceDate, String rank, String driver,String desc){
//        this.title = title;
//        this.circuit = circuit;
//        this.raceDate = raceDate;
//        this.rank = rank;
//        this.driver = driver;
//        this.desc = desc;
//    }

}
