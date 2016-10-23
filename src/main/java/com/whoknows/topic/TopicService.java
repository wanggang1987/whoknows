/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whoknows.topic;

import com.whoknows.domain.Topic;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopicRepository topicRepository;

    public boolean postTopic(Topic topic) {
        log.info(ToStringBuilder.reflectionToString(topic, ToStringStyle.MULTI_LINE_STYLE));
        try {
            topicRepository.postTopic(topic);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}
