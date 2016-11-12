package com.whoknows.utils;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public class Highlighter {

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
		String newtext = text.replaceAll(searchString, "<span style=\"color: #ffffff; background-color: #008bac\"><b>" + searchString + "</b></span>");
		return new DataNode(newtext, textNode.baseUri());
	}

}
