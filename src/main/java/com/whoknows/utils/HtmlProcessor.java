package com.whoknows.utils;

import com.whoknows.search.ShortContent;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public class HtmlProcessor {

	public static ShortContent getHighLightShortText(String searchString, String htmlString, int limit) {
		ShortContent shortContent = new ShortContent();
		shortContent.setFullAble(Boolean.FALSE);
		String str = Jsoup.parse(htmlString.trim()).text();
		if (str.length() > limit) {
			shortContent.setFullAble(Boolean.TRUE);
			str = str.substring(0, limit);
		}
		shortContent.setText(getHighlightedHtml(searchString, str));
		return shortContent;
	}

	public static ShortContent getShortText(String htmlString, int limit) {
		ShortContent shortContent = new ShortContent();
		shortContent.setFullAble(Boolean.FALSE);
		String str = Jsoup.parse(htmlString.trim()).text();
		if (str.length() > limit) {
			shortContent.setFullAble(Boolean.TRUE);
			str = str.substring(0, limit);
		}
		shortContent.setText(str);
		return shortContent;
	}

	public static String getHighlightedHtml(String searchString, String htmlString) {

		Document doc = Jsoup.parse(htmlString);
		final List<TextNode> nodesToChange = new ArrayList<TextNode>();

		NodeTraversor nd = new NodeTraversor(new NodeVisitor() {

			@Override
			public void tail(Node node, int depth) {
				if (node instanceof TextNode) {
					TextNode textNode = (TextNode) node;
					nodesToChange.add(textNode);
				}
			}

			@Override
			public void head(Node node, int depth) {
			}
		});
		nd.traverse(doc.body());

		for (TextNode textNode : nodesToChange) {
			Node newNode = buildElementForText(searchString, textNode);
			textNode.replaceWith(newNode);
		}
		return doc.body().html();
	}

	private static Node buildElementForText(String searchString, TextNode textNode) {
		String text = textNode.getWholeText();
		String newtext = text.replaceAll(searchString, "<span class=\"highLight\">" + searchString + "</span>");
		return new DataNode(newtext, textNode.baseUri());
	}

}
