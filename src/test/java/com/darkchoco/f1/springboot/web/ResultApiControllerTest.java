//package com.darkchoco.f1.springboot.web;
//
//import com.darkchoco.f1.domain.result.RaceResult;
//import com.darkchoco.f1.domain.result.RaceResultRepository;
//import com.darkchoco.f1.web.dto.ResultSaveRequestDto;
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ResultApiControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private RaceResultRepository resultRepository;
//
//    @After
//    public void tearDown() throws Exception{
//        resultRepository.deleteAll();
//    }
//
//    @Test
//    public void Result_save() throws Exception{
//        //given
//        String title = "title";
//        String raceDate = "raceDate";
//        String circuit = "circuit";
//        String ranking = "ranking";
//        String driver = "driver";
//        String notes = "notes";
//
//        ResultSaveRequestDto requestDto = ResultSaveRequestDto.builder()
//                .title(title)
//                .circuit(circuit)
//                .raceDate(raceDate)
//                .ranking(ranking)
//                .driver(driver)
//                .notes(notes)
//                .build();
//
//        String url ="http://localhost:" + port + "/api/v1/result";
//
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<RaceResult> all = resultRepository.findAll();
//        System.out.println(all);
//        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getCircuit()).isEqualTo(circuit);
//        assertThat(all.get(0).getRaceDate()).isEqualTo(raceDate);
//        assertThat(all.get(0).getRanking()).isEqualTo(ranking);
//        assertThat(all.get(0).getDriver()).isEqualTo(driver);
//        assertThat(all.get(0).getNotes()).isEqualTo(notes);
//
//    }
//}
