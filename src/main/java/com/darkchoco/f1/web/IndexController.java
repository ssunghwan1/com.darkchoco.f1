package com.darkchoco.f1.web;

import com.darkchoco.f1.domain.result.RaceResult;
import com.darkchoco.f1.service.posts.PostsService;
import com.darkchoco.f1.service.result.RaceResultService;
import com.darkchoco.f1.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final RaceResultService resultService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("posts", postsService.findAllDesc());
//        return "index";
//    }
    @GetMapping("/")
    public String index(Model model) {
        //model.addAttribute("result", resultService.findByTitle("FM2021"));
        model.addAttribute("result", resultService.findRankByTitle("100퍼"));
        return "index";
    }
    @GetMapping("/posts/save")
    public String postSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/enterResult")
    public String utilitiesBorder(Model model) {
        List<HashMap<String, String>> list = new ArrayList<>();
        List<Map<String, Object>> driverList = new ArrayList<>();
        HashMap<String, String> input = new HashMap<>();
        int num =1;
        for (int i = 0; i < 10; i++) {
            input.put("row", Integer.toString(i + 1));
            input.put("rank_left", Integer.toString(num));
            num++;
            input.put("rank_right", Integer.toString(num));
            num++;
            list.add(input);
            input = new HashMap<>();
        }
        System.out.println(list);
//        driverList.add("다크초코");
//        driverList.add("잉저스트");
        driverList = resultService.findAllDriver();
        model.addAttribute("result", list);
        model.addAttribute("driverList", driverList);
        //System.out.println(model);
        return "enterResult";
    }

    @GetMapping("/leagueResult")
    public String leagueResult(Model model) {
        List<RaceResult> raceResults =  resultService.findAll();
        model.addAttribute("raceResults", raceResults);
        System.out.println(model);
        return "leagueResult";
    }
    @GetMapping("/rating")
    public String rating(Model model) {
        List<Map<String, Object>> raceDate = resultService.findRaceDate("");
        String firstRaceDate = raceDate.get(raceDate.size()-1).get("race_date").toString();

        System.out.println(firstRaceDate);

        model.addAttribute("ratingResult", resultService.findRating(firstRaceDate));
        return "rating";
    }

}
