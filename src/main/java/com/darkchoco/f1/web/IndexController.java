package com.darkchoco.f1.web;

import com.darkchoco.f1.service.posts.PostsService;
import com.darkchoco.f1.service.result.ResultService;
import com.darkchoco.f1.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final ResultService resultService;

//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("posts", postsService.findAllDesc());
//        return "index";
//    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("result", resultService.findByTitle("100퍼"));
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
        model.addAttribute("result", list);
        return "enterResult";
    }

}
