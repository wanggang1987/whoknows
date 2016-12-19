package com.whoknows.paper;

import com.whoknows.search.Paging;
import java.util.List;

public class PaperListResponse {

	private List<PaperDetail> papers;
	private Paging paging;

	public List<PaperDetail> getPapers() {
		return papers;
	}

	public void setPapers(List<PaperDetail> papers) {
		this.papers = papers;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

}
