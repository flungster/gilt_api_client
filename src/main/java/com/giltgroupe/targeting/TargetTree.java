package com.giltgroupe.targeting;

import com.giltgroupe.model.product.Product;
import com.giltgroupe.targeting.node.CharacterNode;

import java.util.Collection;

import org.apache.log4j.Logger;

public class TargetTree {

	private CharacterNode _rootCharacterNode = new CharacterNode();

	private static Logger _logger = Logger.getLogger(TargetTree.class);

	public Collection<Product> findProductsForKeyword(String keyword) {
		_logger.info("Finding Products for keyword: " + keyword);
		Collection<Product> products = null;
		
		CharacterNode currentNode = _rootCharacterNode;
		String lowerKeyword = keyword.toLowerCase();
		boolean keywordFound = true;

		for (int i = 0; i < lowerKeyword.length(); i++) {
			Character keyChar = lowerKeyword.charAt(i);
			_logger.info("char: " + keyChar);
			currentNode = currentNode.findNextCharacterNode(keyChar);
			if (currentNode == null) {
				keywordFound = false;
				break;
			}
		}

		if (keywordFound) {
			_logger.info("Found some products");
			products = currentNode.getProducts();
		} else {
			_logger.info("Did not find products for keyword: " + keyword);
		}

		return products;
	}

	public void addProductForKeyword(String keyword, Product product) {
		_logger.info("Adding product to keyword " + keyword);
		String lowerKeyword = keyword.toLowerCase();

		CharacterNode currentNode = _rootCharacterNode;

		for (int i = 0; i < lowerKeyword.length(); i++) {
			
			Character keyChar = lowerKeyword.charAt(i);
			_logger.info("char: " + keyChar);
			CharacterNode nextNode = currentNode.findNextCharacterNode(keyChar);
			if (nextNode == null) {
				_logger.info("Adding new node");
				nextNode = currentNode.addNextCharacterNode(keyChar);
			}
			currentNode = nextNode;
		}

		_logger.info("Adding product " + product.getName());
		currentNode.addProduct(product);
	}

}