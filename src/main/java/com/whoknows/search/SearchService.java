package com.whoknows.search;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SearchDAO searchDAO;

    public SearchResponse searchByKeyWord(String key) {
        log.info("search:{}", key);
        if (StringUtils.isEmpty(key)) {
            return null;
        }

        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setTopics(searchDAO.searchTopicByKeyWord(key));
        searchResponse.getTopics().addAll(searchDAO.searchTagByKeyWord(key));
        searchResponse.setUser(searchDAO.searchUserByKeyWord(key));
        return searchResponse;
    }
}