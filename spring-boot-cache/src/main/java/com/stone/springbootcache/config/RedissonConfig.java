package com.stone.springbootcache.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.redisson.spring.session.config.EnableRedissonHttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author honorstone
 * @date 2019/3/29 18:38
 */
@Configuration
@EnableCaching
public class RedissonConfig {

    //@Value("${spring.redis.url}")
    private String address ="redis://127.0.0.1:6379";


    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson(){
        Config config = new Config();
        config.setCodec(JsonJacksonCodec.INSTANCE);
        config.useSingleServer()
                .setAddress(address);
        return Redisson.create(config);
    }


    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient){
        Map<String, CacheConfig> config = new HashMap<>();
        config.put("NormalMap", new CacheConfig(24*60*1000, 12*60*1000));
        return new RedissonSpringCacheManager(redissonClient);
    }
}
