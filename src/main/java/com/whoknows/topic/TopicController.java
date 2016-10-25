package com.whoknows.topic;

import com.whoknows.domain.Topic;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/topic")
public class TopicController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TopicService topicService;

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity createTopic(@RequestBody Topic topic) {
        log.info(ToStringBuilder.reflectionToString(topic, ToStringStyle.MULTI_LINE_STYLE));
        if (topicService.createTopic(topic)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity updateTopic(@RequestBody Topic topic) {
        log.info(ToStringBuilder.reflectionToString(topic, ToStringStyle.MULTI_LINE_STYLE));
        if (topicService.updateTopic(topic)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteTopic(@RequestBody Topic topic) {
        log.info(ToStringBuilder.reflectionToString(topic, ToStringStyle.MULTI_LINE_STYLE));
        if (topicService.deleteTopic(topic)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity deleteTopic(Long id) {
        Topic topic = topicService.getTopic(id);
        log.info(ToStringBuilder.reflectionToString(topic, ToStringStyle.MULTI_LINE_STYLE));
        if (topic != null) {
            return ResponseEntity.ok(topic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
