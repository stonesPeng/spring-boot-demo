package com.stone.springbootmongodb.domain;

import lombok.Data;

/**
 * @author honorstone
 * @date 2019/10/14 11:13
 */
@Data
public class Doctor {

    private Long id;

    private String name;

    private String belongHospital;
}
