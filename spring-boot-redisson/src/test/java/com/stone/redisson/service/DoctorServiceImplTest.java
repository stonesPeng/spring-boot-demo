package com.stone.redisson.service;

import com.stone.redisson.bean.Doctor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.Doc;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoctorServiceImplTest {

    @Autowired
    private DoctorService doctorService;

    @Test
    public void cacheDoctor() throws IOException {
        Doctor doctor = new Doctor();
        doctor.setId(1);
        doctor.setName("123");
        doctorService.cacheDoctor(doctor);
    }

    @Test
    public void fetchDoctor() {
    }
}