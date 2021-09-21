package com.example.aop;



import com.example.aop.member.Member;
import com.example.aop.member.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDB implements CommandLineRunner {

    private MemberServiceImpl memberService;

    InitDB(MemberServiceImpl memberService){
        this.memberService = memberService;
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i = 0; i < 100; i++){
            memberService.saveMember(new Member(i+"번째 사용자", i+"@email.com"));
        }
    }
}
