package com.simon.water.dao;

import com.simon.water.domain.SmsRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * creat by 郎亚坤
 * 2022/6/20 20:11
 */

@Repository
public interface SmsRecordDao extends CrudRepository<SmsRecord, Long> {
}

