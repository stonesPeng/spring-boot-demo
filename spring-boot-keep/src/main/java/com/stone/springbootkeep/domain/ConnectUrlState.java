package com.stone.springbootkeep.domain;

import lombok.Data;

/**
 * @author honorstone
 * @date 2019/3/12 9:53
 */
@Data
public class ConnectUrlState {

    private String jobGroupName;

    private String jobName;

    private String targetUrl;

    private Integer noResponseCount;

    private boolean alive;

}
