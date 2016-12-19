package com.whoknows.topic;

import com.whoknows.domain.Topic;
import com.whoknows.tag.TagSelect;
import java.util.List;

public class TopicCreateMessage {

	private Topic topic;
	private List<TagSelect> tags;

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public List<TagSelect> getTags() {
		return tags;
	}

	public void setTags(List<TagSelect> tags) {
		this.tags = tags;
	}
}
