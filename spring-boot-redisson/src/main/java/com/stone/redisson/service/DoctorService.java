package com.stone.redisson.service;

import com.stone.redisson.bean.Doctor;

import java.io.IOException;

/**
 * @author honorstone
 * @date 2019/3/29 17:08
 */
public interface DoctorService {



    void cacheDoctor(Doctor doctor) throws IOException;
    void fetchDoctor(int id);
}
