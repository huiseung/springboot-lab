package com.example.aop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberServiceImpl memberService;

    @GetMapping("/api/members")
    public List<Member> getMembers(){
        return memberService.getMembers();
    }
}
