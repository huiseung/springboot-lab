package com.example.swagger;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String home(){
        return "Hello Swagger Test Web";
    }

    @GetMapping("/api/members")
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    @GetMapping("/api/member/{id}")
    public Member findMember(@PathVariable Long id){
        return memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 id에 member가 없습니다"));
    }

    @PostMapping("/api/member")
    public Member saveMember(@RequestBody Member member){
        return memberRepository.save(member);
    }

    @PutMapping("/api/member")
    public Member updateMember(@RequestBody Member member){
        return memberRepository.save(member);
    }

    @DeleteMapping("/api/member/{id}")
    public String deleteMember(@PathVariable Long id){
        memberRepository.deleteById(id);
        return "delete";
    }

}
