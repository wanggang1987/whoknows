package com.whoknows.bi;

import com.whoknows.domain.TargetType;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BiService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BiRepository biRepository;

	@Value("${rank.topic}")
	private Integer rankTopic;
	@Value("${rank.reply}")
	private Integer rankReply;
	@Value("${rank.comment}")
	private Integer rankComment;
	@Value("${rank.like}")
	private Integer rankLike;
	@Value("${rank.follow}")
	private Integer rankFollow;
	@Value("${rank.paper}")
	private Integer rankPaper;

	public void countRank(RankCollection rankCollection) {
		try {
			biRepository.countPaper().stream().forEach(biEntity
					-> addRank(rankCollection.getUserRank(), biEntity.getTarget_id(), rankPaper));
			biRepository.countTopic().stream().forEach(biEntity
					-> addRank(rankCollection.getUserRank(), biEntity.getTarget_id(), rankTopic));
			biRepository.countReply().stream().forEach(biEntity
					-> addRank(rankCollection.getUserRank(), biEntity.getTarget_id(), rankReply));
			biRepository.countComment().stream().forEach(biEntity
					-> addRank(rankCollection.getUserRank(), biEntity.getTarget_id(), rankComment));

			biRepository.countLike().stream().forEach(biEntity -> {
				if (TargetType.user.name().equals(biEntity.getTarget_type())) {
					addRank(rankCollection.getUserRank(), biEntity.getTarget_id(), rankLike);
				} else if (TargetType.tag.name().equals(biEntity.getTarget_type())) {
					addRank(rankCollection.getTagRank(), biEntity.getTarget_id(), rankLike);
				} else if (TargetType.paper.name().equals(biEntity.getTarget_type())) {
					addRank(rankCollection.getPaperRank(), biEntity.getTarget_id(), rankLike);
				} else if (TargetType.topic.name().equals(biEntity.getTarget_type())) {
					addRank(rankCollection.getTopicRank(), biEntity.getTarget_id(), rankLike);
				} else if (TargetType.reply.name().equals(biEntity.getTarget_type())) {
					addRank(rankCollection.getReplayRank(), biEntity.getTarget_id(), rankLike);
				}
			});
			biRepository.countFollow().stream().forEach(biEntity -> {
				if (TargetType.user.name().equals(biEntity.getTarget_type())) {
					addRank(rankCollection.getUserRank(), biEntity.getTarget_id(), rankFollow);
				} else if (TargetType.tag.name().equals(biEntity.getTarget_type())) {
					addRank(rankCollection.getTagRank(), biEntity.getTarget_id(), rankFollow);
				} else if (TargetType.paper.name().equals(biEntity.getTarget_type())) {
					addRank(rankCollection.getPaperRank(), biEntity.getTarget_id(), rankFollow);
				} else if (TargetType.topic.name().equals(biEntity.getTarget_type())) {
					addRank(rankCollection.getTopicRank(), biEntity.getTarget_id(), rankFollow);
				} else if (TargetType.reply.name().equals(biEntity.getTarget_type())) {
					addRank(rankCollection.getReplayRank(), biEntity.getTarget_id(), rankFollow);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void flushRank(RankCollection rankCollection) {
		try {
			biRepository.flushUserRank(rankCollection.getUserRank());
			biRepository.flushTagRank(rankCollection.getTagRank());
			biRepository.flushPaperRank(rankCollection.getPaperRank());
			biRepository.flushTopicRank(rankCollection.getTopicRank());
			biRepository.flushReplyRank(rankCollection.getReplayRank());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addRank(Map<Long, BiEntry> map, Long id, Integer rank) {
		if (!map.containsKey(id)) {
			BiEntry biEntry = new BiEntry(0, id, null);
			map.put(id, biEntry);
		}
		map.get(id).plus(rank);
	}
}
