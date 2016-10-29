package com.whoknows.tag;

import com.whoknows.domain.ActionType;
import com.whoknows.domain.Tag;
import com.whoknows.wkMessage.topic.TopicSelectResponse;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TagRepository tagRepository;

	public boolean addTag(Tag tag) {
		if (StringUtils.isEmpty(tag.getName())) {
			return false;
		}

		tag.setAction(ActionType.active.toString());
		try {
			tagRepository.addTag(tag);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean deleteTag(Tag tag) {
		if (tag.getId() == null) {
			return false;
		}

		try {
			tagRepository.deleteTag(tag);
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	public List<TopicSelectResponse> getTagList() {
		try {
			return tagRepository.getTagList();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public List<Tag> getTagList(String tagName) {
		try {
			return tagRepository.getTagList(tagName);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
