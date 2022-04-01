package com.darkchoco.f1.service.result;


import com.darkchoco.f1.domain.result.RaceResult;
import com.darkchoco.f1.domain.result.RaceResultRepository;
import com.darkchoco.f1.web.dto.ResultSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


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
        return resultRepository.saveAll(this.requestDtoList(requestDtoList));
    }
    public List<RaceResult> findAll(){
        return resultRepository.findAll();
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
                "sum(point) as point , driver from  race_result    where title = ?  and  driver !='' group by driver order by rank ",title);
        return result;
    }

    public List<Map<String, Object>> findAllDriver (){
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT DISTINCT(DRIVER) FROM race_result");
        return result;
    }

    public List<Map<String, Object>> findRating (String raceDate){
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT \tcount(*) as count,\n" +
                "\tsum(point) as point ,\n" +
                "\tdriver " +
                "from  race_result    where race_date >= ?  and  driver !='' group by driver having count(*) >=5",raceDate);

        List<Map<String, Object>> ratingResult = new ArrayList<>();
        int point;
        int count;
        double rating;

        //(획득점수/참여횟수) * 100 + 10 * 참여횟수
        for(int i=0; i< result.size(); i++){
            point = Integer.parseInt(result.get(i).get("point").toString());
            count = Integer.parseInt(result.get(i).get("count").toString());
            //rating = ((double)point/(double)count)*100 + (double) count*10;
            rating = ((double)point/(double)count)*100;
            result.get(i).put("rating", Math.round(rating));
        }
        System.out.println(result);
        Collections.sort(result, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Double rating1= Double.valueOf(o1.get("rating").toString());
                Double rating2= Double.valueOf(o2.get("rating").toString());

                return rating2.compareTo(rating1);
            }
        });
        for(int i=0; i<result.size(); i++){
            result.get(i).put("rank",i+1);
        }


        System.out.println(result);
        return result;
    }

    public List<Map<String, Object>> findRaceDate (String title){
        List<Map<String, Object>> result = jdbcTemplate.queryForList("SELECT distinct(race_date) from race_result order by race_date desc");
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
