package com.whoknows.bi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BiConfig {

	@Autowired
	private BiService biService;

	@Scheduled(cron = "0 * * * * ?")
	public void rankCount() {
		RankCollection rankCollection = new RankCollection();

		biService.countRank(rankCollection);
		biService.flushRank(rankCollection);
	}
}
