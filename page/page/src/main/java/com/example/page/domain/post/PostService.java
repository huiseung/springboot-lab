package com.example.page.domain.post;

import com.example.page.web.dto.PostListResponseDto;
import com.example.page.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostService {
    private final  PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto){
        return postRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllOrderByIdDesc(Integer pageNum, Integer postPerPage){
        //사이트에서 보여주는 page 번호는 1부터지만 실제로는 0부터 시작이라 맞춰주기 위해 1을 뺀다.
        Pageable pageable = PageRequest.of(pageNum-1, postPerPage, Sort.by("id").descending());
        Page<Post> page = postRepository.findAll(pageable);
        return page.stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    public Long count(){
        return postRepository.count();
    }

}
