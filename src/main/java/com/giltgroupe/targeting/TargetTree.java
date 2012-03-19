package com.giltgroupe.targeting;

import com.giltgroupe.model.product.Product;
import com.giltgroupe.targeting.node.CharacterNode;

import java.util.Collection;

import org.apache.log4j.Logger;

public class TargetTree {

	private CharacterNode _rootCharacterNode = new CharacterNode();

	private static Logger _logger = Logger.getLogger(TargetTree.class);

	public Collection<Product> findProductsForKeyword(String keyword) {
		Collection<Product> products = null;
		
		CharacterNode currentNode = _rootCharacterNode;
		String lowerKeyword = keyword.toLowerCase();
		boolean keywordFound = true;

		for (int i = 0; i < lowerKeyword.length(); i++) {
			Character keyChar = lowerKeyword.charAt(i);
			currentNode = currentNode.findNextCharacterNode(keyChar);
			if (currentNode == null) {
				keywordFound = false;
				break;
			}
		}

		if (keywordFound) {
			products = currentNode.getProducts();
		} 

		return products;
	}

	public void addProductForKeyword(String keyword, Product product) {
		String lowerKeyword = keyword.toLowerCase();

		CharacterNode currentNode = _rootCharacterNode;

		for (int i = 0; i < lowerKeyword.length(); i++) {
			
			Character keyChar = lowerKeyword.charAt(i);
			CharacterNode nextNode = currentNode.findNextCharacterNode(keyChar);
			if (nextNode == null) {
				nextNode = currentNode.addNextCharacterNode(keyChar);
			}
			currentNode = nextNode;
		}

		currentNode.addProduct(product);
	}

}