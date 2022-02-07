package com.darkchoco.f1.service.result;


import com.darkchoco.f1.domain.result.RaceResult;
import com.darkchoco.f1.domain.result.RaceResultRepository;
import com.darkchoco.f1.web.dto.ResultSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class RaceResultService {
    private final RaceResultRepository resultRepository;
    private final JdbcTemplate jdbcTemplate;
    @Transactional
    public Long save(ResultSaveRequestDto requestDto){
        return resultRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<RaceResult> saveAll(List<ResultSaveRequestDto> requestDtoList){
//        result.requestDtoList(requestDtoList);
//        List<RaceResult> entityList = new ArrayList<>();
//        for(int i=0; i< requestDtoList.size();i++){
//            entityList.add(requestDtoList.get(i).toEntity());
//        }
        return resultRepository.saveAll(this.requestDtoList(requestDtoList));
    }
    public List<RaceResult> findByTitle (String title){
        return resultRepository.findAllByTitle(title);
    }

    public List<RaceResult> findDriverStandings (String title){
        return this.driverStandings(title);
    }

    //Business Logig
    public List<RaceResult> requestDtoList(List<ResultSaveRequestDto> requestDtoList){
        List<RaceResult> entityList = new ArrayList<>();
        for(int i=0; i< requestDtoList.size();i++){
            entityList.add(requestDtoList.get(i).toEntity());
        }
        System.out.println(entityList);
        return entityList;
    }
    public List<RaceResult> driverStandings(String title){

        return null;
    }

    public List<Map<String, Object>> findRankByTitle (String title){
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT rank () over (order by sum(point) desc) as rank," +
                "sum(point) as point , driver from  race_result    where title = ? group by driver order by rank ",title);
        return result;
    }

//    @Transactional
//    public Long update(Long id, ResultUpdateRequestDto requestDto){
//        RaceResult result = resultRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
//        RaceResult.update(requestDto.getTitle(), requestDto.get);
//
//        return id;
//    }

//    public ResultResponseDto findById (Long id){
//        RaceResult entity = resultRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
//
//        return new ResultResponseDto(entity);
//    }


//    @Transactional(readOnly = true)
//    public List<ResultListResponseDto> findAllDesc() {
//        return resultRepository.findAllDesc().stream()
//                .map(ResultListResponseDto::new)
//                .collect(Collectors.toList());
//    }

//    @Transactional
//    public void delete(Long id){
//        RaceResult RaceResult = resultRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
//
//        resultRepository.delete(RaceResult);
//    }

}
