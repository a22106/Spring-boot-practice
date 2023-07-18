package com.pius.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {

	@Bean // 템플릿 지원 추가
	public RedisOperations<String, Aircraft> redisOperations(RedisConnectionFactory factory){
		// 객체와 JSON레코드 간 변환 시 사용할 Serializer를 생성.
		// Jackson2JsonRedisSerializer는 Jackson JSON 라이브러리를 사용해 객체를 JSON으로 변환하고
		// JSON을 객체로 변환한다.
		Jackson2JsonRedisSerializer<Aircraft> serializer
				= new Jackson2JsonRedisSerializer<>(Aircraft.class);

		// RedisTemplate은 RedisConnectionFactory를 사용해 Redis 서버에 연결하고
		// RedisOperations 인터페이스를 구현한다.
		RedisTemplate<String, Aircraft> template = new RedisTemplate<>();

		// 기본 serializer로 사용하기 위해 template에 serializer를 설정한다.
		template.setConnectionFactory(factory);
		template.setDefaultSerializer(serializer);
		template.setKeySerializer(new StringRedisSerializer());

		return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

