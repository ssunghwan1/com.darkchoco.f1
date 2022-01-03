package com.darkchoco.f1.web;

import com.darkchoco.f1.domain.result.Result;
import com.darkchoco.f1.service.result.ResultService;
import com.darkchoco.f1.web.dto.ResultResponseDto;
import com.darkchoco.f1.web.dto.ResultSaveRequestDto;
import com.darkchoco.f1.web.dto.ResultUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ResultApiController {

    private final ResultService resultService;

    @PostMapping("/api/v1/result")
    public Long save(@RequestBody ResultSaveRequestDto requestDto){
        return resultService.save(requestDto);
    }

    @PostMapping("/api/v1/resultAll")
    public List<Result> saveAll(@RequestBody List<ResultSaveRequestDto> requestDtoList){
        return resultService.saveAll(requestDtoList);
    }

    @GetMapping("/api/v1/Result/{title}")
    public List<ResultResponseDto> findbyTitle (@PathVariable String title){
        return resultService.findByTitle(title);
    }

//    @PutMapping("/api/v1/Result/{id}")
//    public Long update(@PathVariable Long id, @RequestBody ResultUpdateRequestDto requestDto){
//        return ResultService.update(id, requestDto);
//    }

//    @GetMapping("/api/v1/Result/{id}")
//    public ResultResponseDto findByTitle (@PathVariable String title){
//        return resultService.findByTitle(title);
//    }

//    @DeleteMapping("/api/v1/Result/{id}")
//    public Long delete (@PathVariable Long id){
//        ResultService.delete(id);
//        return id;
//    }
}
