package com.giltgroupe.targeting;

import com.giltgroupe.model.product.Product;

import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Targeting {
    private static String DELIMITERS = " .,;:-";

	public TargetTree _targetTree = new TargetTree();

	public void addProduct(String phrase, Product product) {
		StringTokenizer tokenizer = new StringTokenizer(phrase, DELIMITERS);
		while (tokenizer.hasMoreTokens()) {
			_targetTree.addProductForKeyword(tokenizer.nextToken(), product);
		}
	}

	public Collection<Product> getProductsForKeywordAnd(String phrase) {
		StringTokenizer tokenizer = new StringTokenizer(phrase);
		Collection<Product> intersectionProducts = null;
		while (tokenizer.hasMoreTokens()) {
			Collection<Product> tokenProducts = _targetTree.findProductsForKeyword(tokenizer.nextToken());
			if (tokenProducts == null) {
				continue;
			}

			if (intersectionProducts == null) {
				intersectionProducts = new HashSet<Product>(tokenProducts);
			} else {
				intersectionProducts.retainAll(tokenProducts);
			}
		}

		return intersectionProducts;
	}

}