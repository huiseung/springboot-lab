package com.example.aop.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    public void saveMember(Member member){
        memberRepository.save(member);
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public void updateMember(Member member) throws Exception {
        memberRepository.save(member);
    }
}
