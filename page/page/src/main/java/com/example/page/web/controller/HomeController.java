package com.example.page.web.controller;


import com.example.page.domain.post.Post;
import com.example.page.domain.post.PostRepository;
import com.example.page.domain.post.PostService;
import com.example.page.utils.Paginator;
import com.example.page.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.Map;


@RequiredArgsConstructor
@Controller
public class HomeController {
    private final PostService postService;

    private static final Integer page_per_block = 5;
    private static final Integer post_per_page = 10;

    @PostConstruct
    public void init(){
        for(int i = 1; i <= 100; i++){
            postService.save(PostSaveRequestDto.builder()
                    .title("title" + Integer.toString(i))
                    .content("content" + Integer.toString(i))
                    .author("author" + Integer.toString(i))
                    .build());
        }
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "page", defaultValue = "1")Integer page, Model model){
        try{
            Paginator paginator = new Paginator(page_per_block, post_per_page, postService.count());
            Map<String, Object> pageInfo = paginator.getFixedBlock(page);
            model.addAttribute("pageInfo", pageInfo);
        }catch(IllegalStateException e){
            model.addAttribute("pageInfo", null);
            System.err.println(e);
        }
        model.addAttribute("posts", postService.findAllOrderByIdDesc(page, post_per_page));
        return "home";
    }
}
