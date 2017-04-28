package com.banamex.nearshore.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

//@Configuration
//@PropertySource("classpath:application.properties")
//public class RedisConfiguration {
//
//@Value("${spring.redis.host}")
//private String redisHostName;
//
//@Value("${spring.redis.port}")
//private int redisPort;
//
//@Value("${spring.redis.password}")
//private String redisPassword;
//
//@Bean
//public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//return new PropertySourcesPlaceholderConfigurer();
//}
//
//@Bean
//JedisConnectionFactory jedisConnectionFactory() {
//JedisConnectionFactory factory = new JedisConnectionFactory();
//factory.setHostName(redisHostName);
//factory.setPort(redisPort);
//factory.setPassword(redisPassword);
//factory.setUsePool(true);
//factory.afterPropertiesSet();
//return factory;
//}
//
//@Bean
//RedisTemplate<?, ?> redisTemplate() {
//RedisTemplate<?, ?> redisTemplate = new RedisTemplate<Object, Object>();
//redisTemplate.setConnectionFactory(jedisConnectionFactory());
//return redisTemplate;
//}
//
//@Bean
//RedisCacheManager cacheManager() {
//RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
//return redisCacheManager;
//}
//}