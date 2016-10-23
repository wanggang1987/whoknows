/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whoknows.topic;

import com.whoknows.domain.ActionType;
import com.whoknows.domain.Topic;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/topic")
public class TopicController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TopicService topicService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    boolean postTopic(HttpServletRequest request) {
        Topic topic = new Topic();
        topic.setAction(ActionType.pending.toString());
        topic.setUser_id(0L);
        topic.setTitle("title");
        return topicService.postTopic(topic);
    }
}
