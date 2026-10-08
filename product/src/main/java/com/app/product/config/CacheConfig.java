package com.app.product.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import tools.jackson.databind.jsontype.PolymorphicTypeValidator;@Configuration
public class CacheConfig {
	
	@Bean
	RedisCacheConfiguration redisCacheConfiguraton() {
		


        PolymorphicTypeValidator validator =
                BasicPolymorphicTypeValidator.builder()
                        .allowIfBaseType(Object.class)
                        .build();

        GenericJacksonJsonRedisSerializer serializer =
                GenericJacksonJsonRedisSerializer.builder()
                        .enableDefaultTyping(validator)
                        .build();

        return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(1))
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(serializer));
	}
	
	@Bean
	CacheManager cacheManager( RedisConnectionFactory factory) {
		RedisCacheConfiguration redisConfig = redisCacheConfiguraton();
		return RedisCacheManager.builder(factory)
				.cacheDefaults(redisConfig)
				.build();
	}

}
