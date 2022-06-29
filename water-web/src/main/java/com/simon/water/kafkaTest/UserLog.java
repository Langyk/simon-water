package com.simon.water.kafkaTest;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * creat by 郎亚坤
 * 2022/6/26 17:25
 */

@Data
@Accessors(chain = true)
public class UserLog {

    private String userName;
    private String userid;
    private String state;
}

