package com.darkchoco.f1.service.result;


import com.darkchoco.f1.domain.result.Result;
import com.darkchoco.f1.domain.result.ResultRepository;
import com.darkchoco.f1.web.dto.ResultListResponseDto;
import com.darkchoco.f1.web.dto.ResultResponseDto;
import com.darkchoco.f1.web.dto.ResultSaveRequestDto;
import com.darkchoco.f1.web.dto.ResultUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ResultService {
    private final ResultRepository resultRepository;

    @Transactional
    public Long save(ResultSaveRequestDto requestDto){
        return resultRepository.save(requestDto.toEntity()).getId();
    }

//    @Transactional
//    public Long update(Long id, ResultUpdateRequestDto requestDto){
//        Result result = resultRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
//        Result.update(requestDto.getTitle(), requestDto.get);
//
//        return id;
//    }

//    public ResultResponseDto findById (Long id){
//        Result entity = resultRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
//
//        return new ResultResponseDto(entity);
//    }


    @Transactional(readOnly = true)
    public List<ResultListResponseDto> findAllDesc() {
        return resultRepository.findAllDesc().stream()
                .map(ResultListResponseDto::new)
                .collect(Collectors.toList());
    }

//    @Transactional
//    public void delete(Long id){
//        Result Result = resultRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
//
//        resultRepository.delete(Result);
//    }

}
