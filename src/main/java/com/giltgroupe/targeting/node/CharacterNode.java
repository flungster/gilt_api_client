package com.giltgroupe.targeting.node;

import com.giltgroupe.model.product.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CharacterNode {
	private Character _character;
	private Map<Character, CharacterNode> _nextNodes = null;
	private Collection<Product> _products = null;

	public CharacterNode(Character character) {
		setCharacter(character);
	}

	public CharacterNode() {
		setCharacter('\0');
	}

	public void setCharacter(Character character) {
		_character = character;
	}

	public Character getCharacter() {
		return _character;
	}

	public CharacterNode findNextCharacterNode(Character nextCharacter) {
		if (_nextNodes == null) {
			return null;
		}
		return _nextNodes.get(nextCharacter);
	}

	public CharacterNode addNextCharacterNode(Character nextCharacter) {
		if (_nextNodes == null) {
			_nextNodes = new HashMap<Character, CharacterNode>();
		}
		CharacterNode nextNode = new CharacterNode(nextCharacter);
		_nextNodes.put(nextCharacter, nextNode);
		return nextNode;
	}

	public void addNextCharacterNode(CharacterNode nextCharacterNode) {
		_nextNodes.put(nextCharacterNode.getCharacter(), nextCharacterNode);
	}

	public Collection<Product> getProducts() {
		return _products;
	}

	public void addProduct(Product product) {
		if (_products == null) {
			_products = new HashSet<Product>();
		}
		_products.add(product);
	}
}