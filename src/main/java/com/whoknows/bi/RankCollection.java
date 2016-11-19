package com.whoknows.bi;

import java.util.HashMap;
import java.util.Map;

public class RankCollection {

	Map<Long, BiEntry> userRank = new HashMap<>();
	Map<Long, BiEntry> tagRank = new HashMap<>();
	Map<Long, BiEntry> paperRank = new HashMap<>();
	Map<Long, BiEntry> topicRank = new HashMap<>();
	Map<Long, BiEntry> replayRank = new HashMap<>();

	public Map<Long, BiEntry> getUserRank() {
		return userRank;
	}

	public void setUserRank(Map<Long, BiEntry> userRank) {
		this.userRank = userRank;
	}

	public Map<Long, BiEntry> getTagRank() {
		return tagRank;
	}

	public void setTagRank(Map<Long, BiEntry> tagRank) {
		this.tagRank = tagRank;
	}

	public Map<Long, BiEntry> getPaperRank() {
		return paperRank;
	}

	public void setPaperRank(Map<Long, BiEntry> paperRank) {
		this.paperRank = paperRank;
	}

	public Map<Long, BiEntry> getTopicRank() {
		return topicRank;
	}

	public void setTopicRank(Map<Long, BiEntry> topicRank) {
		this.topicRank = topicRank;
	}

	public Map<Long, BiEntry> getReplayRank() {
		return replayRank;
	}

	public void setReplayRank(Map<Long, BiEntry> replayRank) {
		this.replayRank = replayRank;
	}
}
