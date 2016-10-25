package com.whoknows.topic;

import com.whoknows.domain.ActionType;
import com.whoknows.domain.Topic;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopicRepository topicRepository;

    public boolean createTopic(Topic topic) {
        topic.setAction(ActionType.pending.toString());
        if (topic.getUser_id() == null
                || StringUtils.isEmpty(topic.getTitle())
                || StringUtils.isEmpty(topic.getContent())) {
            return false;
        }

        try {
            topicRepository.createTopic(topic);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateTopic(Topic topic) {
        if (topic.getId() == null
                || topic.getUser_id() == null
                || StringUtils.isEmpty(topic.getTitle())
                || StringUtils.isEmpty(topic.getContent())) {
            return false;
        }

        try {
            topicRepository.updateTopic(topic);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteTopic(Topic topic) {
        if (topic.getId() == null) {
            return false;
        }

        try {
            topicRepository.deleteTopic(topic);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public Topic getTopic(Long id) {
        if (id == null) {
            return null;
        }

        try {
            return topicRepository.getTopic(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
