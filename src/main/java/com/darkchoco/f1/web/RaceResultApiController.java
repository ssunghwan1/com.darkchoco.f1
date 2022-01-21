package com.darkchoco.f1.web;

import com.darkchoco.f1.domain.result.RaceResult;
import com.darkchoco.f1.service.result.RaceResultService;
import com.darkchoco.f1.web.dto.ResultSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class RaceResultApiController {

    private final RaceResultService resultService;

    private final JdbcTemplate jdbcTemplate;

    @PostMapping("/api/v1/result")
    public Long save(@RequestBody ResultSaveRequestDto requestDto){
        return resultService.save(requestDto);
    }

    @PostMapping("/api/v1/resultAll")
    public List<RaceResult> saveAll(@RequestBody List<ResultSaveRequestDto> requestDtoList){
        return resultService.saveAll(requestDtoList);
    }

    @GetMapping("/api/v1/rank/{title}")
    public List<RaceResult> findRankByTitle (@PathVariable String title){
        List<Map<String, Object>> test = jdbcTemplate.queryForList("SELECT rank () over (order by sum(point) desc),sum(point) as point , driver from  Race_Result    where title = ? group by driver ",title);
        System.out.println(test);
        return null;
    }

//    @PutMapping("/api/v1/RaceResult/{id}")
//    public Long update(@PathVariable Long id, @RequestBody ResultUpdateRequestDto requestDto){
//        return RaceResultService.update(id, requestDto);
//    }

//    @GetMapping("/api/v1/RaceResult/{id}")
//    public ResultResponseDto findByTitle (@PathVariable String title){
//        return resultService.findByTitle(title);
//    }

//    @DeleteMapping("/api/v1/RaceResult/{id}")
//    public Long delete (@PathVariable Long id){
//        RaceResultService.delete(id);
//        return id;
//    }
}
