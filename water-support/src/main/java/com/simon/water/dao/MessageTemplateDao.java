package com.simon.water.dao;

import com.simon.water.domain.MessageTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageTemplateDao extends CrudRepository<MessageTemplate,Long> {
}
