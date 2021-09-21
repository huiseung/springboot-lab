package com.example.aop.member;

import java.util.List;

public interface MemberService {
    List<Member> getMembers();
    void saveMember(Member member);
    void updateMember(Member member) throws Exception;
}
