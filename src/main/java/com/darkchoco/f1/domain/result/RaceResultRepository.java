package com.darkchoco.f1.domain.result;

import com.darkchoco.f1.web.dto.ResultResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RaceResultRepository extends JpaRepository<RaceResult, Long> {

    @Query("SELECT p FROM RaceResult p ORDER BY p.id DESC")
    List<ResultResponseDto> findAllDesc();

    List<RaceResult> findAllByTitle(String title);

    // @Query("SELECT rank () over (order by sum(point) desc),sum(point) as point , driver from  RaceResult    where title ='FM2021' group by driver ")
    @Query("SELECT sum(point) as point , driver FROM RaceResult group by driver and  driver !='' order by point desc")
    List<RaceResult> findRankByTitle(String title);
}
