package com.example.redis;

import com.example.redis.domain.member.Member;
import com.example.redis.domain.member.MemberRedisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisApplicationTests {
	@Autowired
	private MemberRedisRepository memberRedisRepository;

	@Test
	void redis_test(){
		//given
		Member member = new Member("jo", 20);
		//when
		memberRedisRepository.save(member);
		//then
		memberRedisRepository.findById(member.getId());
		memberRedisRepository.count();
		//after
		memberRedisRepository.delete(member);
	}

}
