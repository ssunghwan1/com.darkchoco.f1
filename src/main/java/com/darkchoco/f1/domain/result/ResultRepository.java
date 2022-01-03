package com.darkchoco.f1.domain.result;

import com.darkchoco.f1.web.dto.ResultResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    @Query("SELECT p FROM Result p ORDER BY p.id DESC")
    List<ResultResponseDto> findAllDesc();

    @Query("SELECT p FROM Result p ORDER BY p.id DESC")
    List<ResultResponseDto> findAllByTitle(String title);
}
