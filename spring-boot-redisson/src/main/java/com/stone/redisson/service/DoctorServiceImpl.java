package com.stone.redisson.service;

import com.stone.redisson.bean.Doctor;
import io.netty.buffer.ByteBuf;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author honorstone
 * @date 2019/3/29 17:10
 */
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private RedissonClient redissonClient;
    @Override
    public void cacheDoctor(Doctor doctor){
        RBucket<Object> bucket = redissonClient.getBucket("0",JsonJacksonCodec.INSTANCE);
        bucket.set(doctor);
        bucket.rename("doc");
    }

    @Override
    public void fetchDoctor(int id) {

    }
}
